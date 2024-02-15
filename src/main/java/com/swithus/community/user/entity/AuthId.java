package com.swithus.community.user.entity;

import com.swithus.community.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table
@ToString
public class AuthId extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    //아이디
    private String userid;

    //비밀번호
    private String userpwd;

}
