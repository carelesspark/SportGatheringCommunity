package com.swithus.community.manager.entity;

import com.swithus.community.board.entity.Promotion;
import com.swithus.community.global.entity.BaseEntity;
import com.swithus.community.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ReportPost extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Promotion post;

    @ManyToOne(fetch = FetchType.LAZY)
    private ReportPostCtgr ctgr;

    private String reason;

    private String nickname;
}
