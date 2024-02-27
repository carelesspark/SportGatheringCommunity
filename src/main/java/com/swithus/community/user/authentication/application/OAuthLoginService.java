package com.swithus.community.user.authentication.application;

import com.swithus.community.login.repository.EasyLoginRepository;
import com.swithus.community.user.authentication.domain.AuthTokens;
import com.swithus.community.user.authentication.domain.AuthTokensGenerator;
import com.swithus.community.user.authentication.domain.oauth.OAuthInfoResponse;
import com.swithus.community.user.authentication.domain.oauth.OAuthLoginParams;
import com.swithus.community.user.authentication.domain.oauth.RequestOAuthInfoService;
import com.swithus.community.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final EasyLoginRepository easyLoginRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Long memberId = findOrCreateMember(oAuthInfoResponse);
        return authTokensGenerator.generate(memberId);
    }

    private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return easyLoginRepository.findByEmail(oAuthInfoResponse.getEmail())
                .map(User::getId)
                .orElseGet(() -> newMember(oAuthInfoResponse));
    }

    private Long newMember(OAuthInfoResponse oAuthInfoResponse) {
        User member = User.builder()
                .email(oAuthInfoResponse.getEmail())
                .nickname(oAuthInfoResponse.getNickname())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();

        return easyLoginRepository.save(member).getId();
    }
}
