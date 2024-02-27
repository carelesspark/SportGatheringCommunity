package com.swithus.community.login.controller;

import com.swithus.community.login.repository.EasyLoginRepository;
import com.swithus.community.user.authentication.domain.AuthTokensGenerator;
import com.swithus.community.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;




@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class EasyLoginUserController {
    private final EasyLoginRepository easyLoginRepository;
    private final AuthTokensGenerator authTokensGenerator;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(easyLoginRepository.findAll());
    }

    @GetMapping("/{accessToken}")
    public ResponseEntity<User> findByAccessToken(@PathVariable String accessToken) {
        Long memberId = authTokensGenerator.extractMemberId(accessToken);
        return ResponseEntity.ok(easyLoginRepository.findById(memberId).get());
    }
}