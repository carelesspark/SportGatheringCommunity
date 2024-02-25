package com.swithus.community.club.repository;

import com.swithus.community.club.entity.ClubPost;
import com.swithus.community.club.entity.ClubPostReply;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubPostReplyRepository extends JpaRepository<ClubPostReply, Long> {
    @EntityGraph(attributePaths = {"writer"}, type = EntityGraph.EntityGraphType.FETCH)
    List<ClubPostReply> findListByPost(ClubPost post);
}
