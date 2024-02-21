package com.swithus.community.board.entity;

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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductCtgr ctgr;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sports sports;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String name;

    private String content;
    // 방문객
    private int visitCount;
    // 팔렸냐
    private byte isDone;

}
