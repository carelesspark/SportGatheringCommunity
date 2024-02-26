package com.swithus.community.manager.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.swithus.community.manager.dto.UserDTO;
import com.swithus.community.manager.dto.WithdrawalUserDTO;
import com.swithus.community.manager.dto.page.UserPageRequestDTO;
import com.swithus.community.manager.dto.page.UserPageResultDTO;
import com.swithus.community.manager.entity.Announcement;
import com.swithus.community.manager.entity.Faq;
import com.swithus.community.manager.entity.QWithdrawalUser;
import com.swithus.community.manager.entity.WithdrawalUser;
import com.swithus.community.manager.repository.UserCountRepository;
import com.swithus.community.manager.repository.UserDetailRepository;
import com.swithus.community.manager.repository.UserRepository;
import com.swithus.community.manager.repository.WithdrawalUserRepository;
import com.swithus.community.manager.service.UserService;
import com.swithus.community.user.entity.AuthId;
import com.swithus.community.user.entity.QAuthId;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final WithdrawalUserRepository withdrawalUserRepository;
    private final UserDetailRepository userDetailRepository;
    private final UserCountRepository userCountRepository;


    @Override
    public Long countBy() {
       return userCountRepository.countBy();
    }

    @Override
    public Long countTodayUser() {
        return userCountRepository.countTodayRegisteredUsers();
    }

    @Override
    public UserPageResultDTO<UserDTO, AuthId> getUserList(UserPageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());

        BooleanBuilder booleanBuilder = getSearch(requestDTO);

        Page<AuthId> result = userRepository.findAll(booleanBuilder, pageable);

        Function<AuthId, UserDTO> fn = (entity -> entityToDto(entity));

        return new UserPageResultDTO(result, fn);
    }

    private BooleanBuilder getSearch(UserPageRequestDTO requestDTO) {
        String search = requestDTO.getSearch();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QAuthId qAuthId = QAuthId.authId;

        BooleanExpression express = qAuthId.id.gt(0L);

        booleanBuilder.and(express);

        if (search == null || search.trim().length() == 0) {
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();
        conditionBuilder.or(qAuthId.userid.containsIgnoreCase(search));
        conditionBuilder.or(qAuthId.user.nickname.containsIgnoreCase(search));

        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }

    @Override
    public UserDTO info(long no) {

        Optional<AuthId> result = userRepository.findById(no);

        return result.isPresent() ? entityToDto(result.get()) : null;
    }

    @Override
    public void withdrawalUser(WithdrawalUserDTO withdrawalUserDTO) {

        log.info(withdrawalUserDTO.getUserId());
        WithdrawalUser entity = dtoToEntity(withdrawalUserDTO);
        withdrawalUserRepository.save(entity);
        userRepository.deleteById(withdrawalUserDTO.getAuthUserId());
        userDetailRepository.deleteById(withdrawalUserDTO.getAuthUserDetailId());

    }

    @Override
    public UserPageResultDTO<WithdrawalUserDTO, WithdrawalUser> getDeletedUserList(UserPageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());

        BooleanBuilder booleanBuilder = getSearchDeletedUser(requestDTO);

        Page<WithdrawalUser> result = withdrawalUserRepository.findAll(booleanBuilder, pageable);

        Function<WithdrawalUser, WithdrawalUserDTO> fn = (entity -> entityToWithdrawalUserDTO(entity));

        return new UserPageResultDTO(result, fn);
    }


    private BooleanBuilder getSearchDeletedUser(UserPageRequestDTO requestDTO) {
        String search = requestDTO.getSearch();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QWithdrawalUser qWithdrawalUser = QWithdrawalUser.withdrawalUser;

        BooleanExpression express = qWithdrawalUser.id.gt(0L);

        booleanBuilder.and(express);

        if (search == null || search.trim().length() == 0) {
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();
        conditionBuilder.or(qWithdrawalUser.userId.contains(search));

        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }

    @Override
    public WithdrawalUserDTO infoDeletedUser(long no) {

        Optional<WithdrawalUser> result = withdrawalUserRepository.findById(no);

        return result.isPresent()? entityToWithdrawalUserDTO(result.get()) : null;
    }


}
