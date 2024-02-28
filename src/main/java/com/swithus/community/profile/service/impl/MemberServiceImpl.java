package com.swithus.community.profile.service.impl;

import com.swithus.community.profile.repository.MemberRepository;
import com.swithus.community.profile.service.MemberService;
import com.swithus.community.user.dto.UserDTO;
import com.swithus.community.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
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

        // JPA가 관리하는 영속성 컨텍스트에서 변경된 내용을 자동으로 감지하고 DB에 반영
        return memberRepository.save(existingUser);
    }
}
