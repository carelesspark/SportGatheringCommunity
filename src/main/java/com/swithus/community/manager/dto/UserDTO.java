package com.swithus.community.manager.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private LocalDateTime modDate;

    private LocalDateTime regDate;

    private String userid;

    private String userpwd;

    private Long userId;

    private String email;

    private String gender;

    private String name;


}
