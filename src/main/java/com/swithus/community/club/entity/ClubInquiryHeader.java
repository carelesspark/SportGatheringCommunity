package com.swithus.community.club.entity;

import com.swithus.community.global.entity.BaseEntity;
import com.swithus.community.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table
@ToString
public class ClubInquiryHeader extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 클럽 키
    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;
    // 문의자 키
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // 마지막 메시지 작성자
    private byte lastSender;
    // 마지막 메시지 시간
    private LocalDateTime lastSendTime;
    // 끝난 문의 내역인가
    private byte isActive;
}
