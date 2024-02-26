package com.swithus.community.club.repository;

import com.swithus.community.club.entity.ClubPost;
import com.swithus.community.club.repository.search.ClubPostSearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ClubPostRepository extends JpaRepository<ClubPost, Long>, ClubPostSearchRepository {
    @Query("select p, count(distinct r), count(distinct l), i " +
            "from ClubPost p " +
            "left join ClubPostReply r on r.post = p " +
            "left join ClubPostLike l on l.post = p " +
            "left join ClubPostImage i on i.post = p " +
            "group by p, i")
    List<Object[]> getClubPostDTO(Long postId);
}
