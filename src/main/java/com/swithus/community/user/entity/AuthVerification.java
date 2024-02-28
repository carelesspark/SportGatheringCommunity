package com.swithus.community.user.entity;

import com.swithus.community.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "auth_verification")
public class AuthVerification extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = true) // user_id는 user엔티티의 키 값 참조
    private User user;

    @Column(name = "is_email_verified", nullable = false)
    private boolean isEmailVerified;

    @Column(name = "verification_code", nullable = true, unique = true)
    private String verificationCode;

    // isEmailVerified에 대한 setter 메서드 추가
    public void setIsEmailVerified(boolean isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    // verificationCode에 대한 setter 메서드 추가
    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    // User에 대한 setter 메서드 추가
    public void setUser(User user) {
        this.user = user;
    }
}
