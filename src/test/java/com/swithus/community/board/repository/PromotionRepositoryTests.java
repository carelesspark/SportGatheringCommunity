package com.swithus.community.board.repository;

import com.swithus.community.board.entity.Promotion;
import com.swithus.community.board.promotion.repository.PromotionBoardRepository;
import com.swithus.community.global.entity.Sports;
import com.swithus.community.global.repository.SportsRepository;
import com.swithus.community.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.stream.IntStream;

@SpringBootTest
public class PromotionRepositoryTests {

    @Autowired
    private PromotionBoardRepository promotionBoardRepository;

    @Test
    public void insertBoard(){
        IntStream.rangeClosed(1,100).forEach(i-> {

            User user = User.builder()
                    .id(1L)
                    .build();

            Sports sports = Sports.builder()
                    .id((long)(i%5+1))
                    .build();

            Promotion promotion = Promotion.builder()
                    .title("Title.." + i)
                    .content("Content.." + i)
                    .writer(user)
                    .sports(sports)
                    .build();

            promotionBoardRepository.save(promotion);
        });
    }


    @Test
    public void test(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("id").descending());

        Page<Object[]> result = promotionBoardRepository.getBoardWithReplyCount(pageable);

        result.get().forEach(row -> {
            Object[] arr = (Object[])row;
            System.out.println(arr);
        });
    }

}
