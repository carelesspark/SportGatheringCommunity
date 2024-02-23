package com.swithus.community.email.repository;

import com.swithus.community.email.entity.Email;
import com.swithus.community.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    public Optional<Email> findByEmail(String email);
}
