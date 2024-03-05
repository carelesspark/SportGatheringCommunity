package com.swithus.community.board.entity;

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
public class PromotionReview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewnum;

    @ManyToOne(fetch = FetchType.LAZY)
    private Promotion promotion;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private int grade;

    private String text;

    public void changeGrade(int grade){this.grade = grade;}
    public void changeText(String text){this.text = text;}


}
