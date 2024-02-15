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
public class ReportPost extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private User reporter_id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Promotion post_id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ReportPostCtgr ctgr_id;

    private String reason;

}
