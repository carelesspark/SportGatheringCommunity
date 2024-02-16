package com.swithus.community.manager.entity;

import com.swithus.community.board.entity.Promotion;
import com.swithus.community.board.entity.PromotionReply;
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
public class ReportComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User reporter;

    @ManyToOne(fetch = FetchType.LAZY)
    private PromotionReply reply;

    @ManyToOne(fetch = FetchType.LAZY)
    private ReportCommentCtgr ctgr;

    private String reason;

}
