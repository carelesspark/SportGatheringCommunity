package com.swithus.community.club.entity;

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
public class GreetingsImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 가입인사 키
    @ManyToOne(fetch = FetchType.LAZY)
    private Greetings greetings;

    // 경로
    private String path;
    // 이름
    private String name;
    // uuid
    private String uuid;
}
