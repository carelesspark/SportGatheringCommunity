package com.swithus.community.board.entity;

import com.swithus.community.global.entity.BaseEntity;
import com.swithus.community.global.entity.Region;
import com.swithus.community.global.entity.Sports;
import com.swithus.community.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ProductBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductCtgr ctgr;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sports sports;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Region region;

    private String title;

    private String content;

    private int visitCount;


}
