package com.swithus.community.board.entity;

import com.swithus.community.club.entity.Club;
import com.swithus.community.global.entity.BaseEntity;
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
public class Promotion extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

    @ManyToOne(fetch = FetchType.LAZY)
    private User writer;

    private String title;

    private String content;

    private int visitCount;

    public void changeTitle(String title){
        this.title = title;
    }

    public void changeContent(String content){
        this.content = content;
    }

    public void plusVisitCount(int visitCount){ this.visitCount = ++visitCount;}

}
