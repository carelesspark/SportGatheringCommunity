package com.swithus.community.profile.repository;

import com.swithus.community.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<User, Long> {
    boolean existsByNickname(String nickname);
}
