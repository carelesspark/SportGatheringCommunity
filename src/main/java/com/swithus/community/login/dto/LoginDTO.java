package com.swithus.community.login.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDTO {

    private String userId;
    private String userPwd;
}