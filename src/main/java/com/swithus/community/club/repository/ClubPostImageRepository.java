package com.swithus.community.club.repository;

import com.swithus.community.club.entity.ClubPostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClubPostImageRepository extends JpaRepository<ClubPostImage, Long> {
    @Query("select i " +
            "from ClubPostImage i " +
            "where i.post.id = :postId")
    List<ClubPostImage> getClubPostImageListByClubPostId(Long postId);
}
