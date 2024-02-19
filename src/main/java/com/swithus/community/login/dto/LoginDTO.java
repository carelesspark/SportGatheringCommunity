package com.swithus.community.login.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
@Getter
@Setter
public class LoginDTO {

    private String loginId;
    private String loginPwd;
}

