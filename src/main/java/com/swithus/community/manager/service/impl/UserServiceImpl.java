package com.swithus.community.manager.service.impl;

import com.swithus.community.manager.dto.UserDTO;
import com.swithus.community.manager.dto.page.UserPageRequestDTO;
import com.swithus.community.manager.dto.page.UserPageResultDTO;
import com.swithus.community.manager.repository.UserRepository;
import com.swithus.community.manager.service.UserService;
import com.swithus.community.user.entity.AuthId;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public UserPageResultDTO<UserDTO, AuthId> getUserList(UserPageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());

        Page<AuthId> result = userRepository.findAll(pageable);

        Function<AuthId, UserDTO> fn = (entity -> entityToDto(entity));

        return new UserPageResultDTO<>(result ,fn);
    }
}
