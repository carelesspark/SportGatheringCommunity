package com.swithus.community.profile.service.impl;

import com.swithus.community.global.exception.DuplicateFormatException;
import com.swithus.community.profile.repository.MemberRepository;
import com.swithus.community.profile.service.MemberService;
import com.swithus.community.register.repository.RegisterRepository;
import com.swithus.community.user.dto.UserDTO;
import com.swithus.community.user.entity.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Log4j2
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final RegisterRepository registerRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, RegisterRepository registerRepository) {
        this.memberRepository = memberRepository;
        this.registerRepository = registerRepository;
    }

    @Override
    public User updateUserInfo(UserDTO userDTO) {
        User existingUser = memberRepository.findById(userDTO.getId())
                .orElseThrow(() -> new RuntimeException("ID가 " + userDTO.getId() + "인 사용자를 찾을 수 없습니다."));

        existingUser.setNickname(userDTO.getNickname());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPost(userDTO.getPost());
        existingUser.setAddr(userDTO.getAddr());
        existingUser.setAddrDetail(userDTO.getAddrDetail());

        log.info(existingUser);

        // JPA가 관리하는 영속성 컨텍스트에서 변경된 내용을 자동으로 감지하고 DB에 반영
        return memberRepository.save(existingUser);
    }

    @Override
    public boolean isUserNicknameExists(String userNickname) {
        return registerRepository.existsByNickname(userNickname);
    }

    @Override
    public boolean isUserEmailExists(String userEmail) {
        return registerRepository.existsByEmail(userEmail);
    }


}
