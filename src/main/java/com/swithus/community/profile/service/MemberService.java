package com.swithus.community.profile.service;

import com.swithus.community.user.dto.UserDTO;
import com.swithus.community.user.entity.User;

public interface MemberService {
    User updateUserInfo(UserDTO userDTO);
}
