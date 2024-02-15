package com.swithus.community.manager.entity;

import com.swithus.community.global.entity.BaseEntity;
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

//    @ManyToOne(fetch = FetchType.LAZY)
//    private User reporter_id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Promotion reply_id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ReportCommentCtgr ctgr;

    private String reason;

}
