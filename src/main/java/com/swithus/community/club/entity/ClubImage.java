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
public class ClubImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 클럽 키
    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

    // 이름
    private String name;
    // 경로
    private String path;
    // uuid
    private String uuid;
}
