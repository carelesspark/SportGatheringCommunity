package com.swithus.community.global.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table
@ToString
public class Position extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sports sports;

    @ManyToOne(fetch = FetchType.LAZY)
    private Region region;

    // 포지션명
    private String name;
}
