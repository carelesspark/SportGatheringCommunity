package com.swithus.community.manager.entity;

import com.swithus.community.board.entity.Promotion;
import com.swithus.community.global.entity.BaseEntity;
import com.swithus.community.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
    private ReportPostCtgr ctgr;

    @ColumnDefault("false")
    @Column(columnDefinition = "TINYINT(1)")
    private boolean isSuitabled;

    @ColumnDefault("false")
    @Column(columnDefinition = "TINYINT(1)")
    private boolean isSolved;

    private String reason;

    private String nickname;

    private String postContent;

    private String postWriter;

    private Long postId;

    public void change_isSolved(boolean isSolved){this.isSolved = isSolved;}
    public void change_isSuitabled(boolean isSuitabled){this.isSuitabled = isSuitabled;}
}
