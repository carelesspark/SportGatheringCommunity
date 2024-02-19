package com.swithus.community.board.announcement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


public class BaseEntity {

    @CreatedDate
    @Column(name="regDate",updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name="modDate")
    private LocalDateTime modDate;
}
