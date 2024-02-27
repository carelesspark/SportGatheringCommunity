package com.swithus.community.user.entity;

import com.swithus.community.global.entity.BaseEntity;
import com.swithus.community.user.authentication.domain.oauth.OAuthProvider;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table
@ToString
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //회원 이름
    private String name;

    //회원 닉네임
    private String nickname;

    //회원 이메일
    private String email;

    //회원 생년월일
    private String birth;

    //회원 성별
    private String gender;

    //회원 주소
    private String addr;

    //회원 상세주소
    private String addrDetail;

    //회원 우편번호
    private String post;

    //클럽장여부
    private byte isLeader;
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    private OAuthProvider oAuthProvider;

    @Builder
    public User(String email, String nickname, OAuthProvider oAuthProvider) {
        this.email = email;
        this.nickname = nickname;
        this.oAuthProvider = oAuthProvider;
    }
}
