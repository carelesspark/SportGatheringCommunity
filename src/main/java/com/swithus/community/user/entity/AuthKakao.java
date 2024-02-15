package com.swithus.community.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table
@ToString
public class AuthKakao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    //억세스 토큰
    private String accessToken;
}
