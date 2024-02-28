package com.swithus.community.user.entity;

import com.swithus.community.global.entity.BaseEntity;
import com.swithus.community.user.authentication.domain.oauth.OAuthProvider;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table
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

    private OAuthProvider oAuthProvider;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AuthVerification authVerification;
//
//    // 'nickname' 필드에 대한 setter 메서드 추가
//    public void setNickname(String nickname) {
//        this.nickname = nickname;
//    }
//
//    public void setAddr(String addr) {
//        this.addr = addr;
//    }
//
//    // 'addrDetail' 필드에 대한 setter 메서드 추가
//    public void setAddrDetail(String addrDetail) {
//        this.addrDetail = addrDetail;
//    }
//
//    // 'post' 필드에 대한 setter 메서드 추가
//    public void setPost(String post) {
//        this.post = post;
//    }
//
//    // 'email' 필드에 대한 setter 메서드 추가
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Long getUserid() {
//        return this.id = id;
//    }

    @Builder
    public User(String email, String nickname, OAuthProvider oAuthProvider) {
        this.email = email;
        this.nickname = nickname;
        this.oAuthProvider = oAuthProvider;
    }

    @Builder
    public User(String email, String nickname, OAuthProvider oAuthProvider) {
        this.email = email;
        this.nickname = nickname;
        this.oAuthProvider = oAuthProvider;
    }
}
