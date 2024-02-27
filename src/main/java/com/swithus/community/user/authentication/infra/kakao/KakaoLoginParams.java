package com.swithus.community.user.authentication.infra.kakao;

import com.swithus.community.user.authentication.domain.oauth.OAuthLoginParams;
import com.swithus.community.user.authentication.domain.oauth.OAuthProvider;
import org.springframework.util.MultiValueMap;

public class KakaoLoginParams implements OAuthLoginParams {

    private String authorizationCode;
    @Override
    public OAuthProvider oAuthProvider() {
        return null;
    }

    @Override
    public MultiValueMap<String, String> makeBody() {
        return null;
    }
}
