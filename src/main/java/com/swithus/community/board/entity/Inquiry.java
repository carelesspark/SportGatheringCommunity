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
public class Inquiry extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    @ManyToOne(fetch = FetchType.LAZY)
    private InquiryCtgr ctgr;


    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String title;

    private String content;

    private boolean isAnswered;

    public void change_isAnswered(boolean isAnswered){this.isAnswered = isAnswered;}
}
