package com.swithus.community.manager.repository;

import com.swithus.community.club.entity.ClubMember;
import com.swithus.community.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDetailRepository extends JpaRepository<User, Long> {
    @Query("select u " +
            "from User u " +
            "where u.nickname = :nickname")
    User getUserByUserNickname(String nickname);

}
