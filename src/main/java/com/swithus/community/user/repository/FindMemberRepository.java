package com.swithus.community.user.repository;

import com.swithus.community.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FindMemberRepository extends JpaRepository<User, Long> {
    User findByEmailAndName(String email, String name);

    User findByEmail(String email);
}
