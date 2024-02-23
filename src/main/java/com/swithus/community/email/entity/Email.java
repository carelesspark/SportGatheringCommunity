package com.swithus.community.email.entity;

import com.swithus.community.user.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "email")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    // 이메일 인증 여부
    @Column(name = "email_status", nullable = false)
    private boolean emailStatus;

    @Builder
    public Email(UserDTO userDTO) {
        this.email = userDTO.getEmail();
        this.emailStatus = false;
    }
}
