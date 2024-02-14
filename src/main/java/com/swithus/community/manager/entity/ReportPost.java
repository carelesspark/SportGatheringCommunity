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
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY)
    private User reporter_key;

    @ManyToOne(fetch = FetchType.LAZY)
    private Promotion post_key;

    @ManyToOne(fetch = FetchType.LAZY)
    private ReportPostCtgr ctgr_key;

    private String reason;

}
