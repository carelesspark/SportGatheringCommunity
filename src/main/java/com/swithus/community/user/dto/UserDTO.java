package com.swithus.community.user.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
@Getter
@Setter
public class UserDTO {
    //User Entity KEY
    private Long id;

    //회원 아이디
    private String userid;

    //회원 비밀번호
    private String userpwd;

    //회원이름
    private String name;

    //회원 닉네임
    private String nickname;

    //회원 이메일
    private String email;

    //인증번호
    private String verifyCode;

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

    public String getVerificationCode() {
        return verifyCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verifyCode = verificationCode;
    }
}
