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
public class Faq extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private FaqCtgr ctgr;

    private String question;

    private String answer;

    public void change_question(String question){this.question = question;}
    public void change_answer(String answer){this.answer = answer;}
    public void change_ctgr(FaqCtgr ctgr) {this.ctgr = ctgr;}

}
