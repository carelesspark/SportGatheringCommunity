package com.swithus.community.profile.service.impl;

import com.swithus.community.profile.repository.ProfileRepository;
import com.swithus.community.profile.service.ProfileService;
import com.swithus.community.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }
    @Override
    public boolean isNicknameExists(String nickname) {
        return profileRepository.existsByNickname(nickname);
    }

    @Override
    @Transactional
    public void updateNickname(Long userId, String newNickname) {
        Optional<User> optionalUser = profileRepository.findById(userId);

        optionalUser.ifPresent(user -> {
            user.setNickname(newNickname);
        });

    }
}
