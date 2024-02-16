package com.swithus.community.manager.entity;

import com.swithus.community.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ReportCommentCheck extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private ReportComment comment;

    @ColumnDefault("false")
    @Column(columnDefinition = "TINYINT(1)")
    private boolean isSuitabled;

    @ColumnDefault("false")
    @Column(columnDefinition = "TINYINT(1)")
    private boolean isSolved;
}
