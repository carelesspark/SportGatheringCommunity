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
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY)
    private FaqCtgr ctgr_key;

    private String question;

    private String answer;

}
