package com.swithus.community.user.entity;

import com.swithus.community.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table
@ToString
public class AuthId extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User 엔티티와의 다대일 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id") // 외래키 컬럼명 지정
    private User user;

    //아이디
    private String userid;

    //비밀번호
    private String userpwd;

//    @Override
//    public String toString() {
//        return "AuthId{" +
//                "id=" + id +
//                ", user=" + user +
//                ", userid='" + userid + '\'' +
//                ", userpwd='" + userpwd + '\'' +
//                '}';
//    }

}
