package com.swithus.community.profile.service;

public interface ProfileService {
    boolean isNicknameExists(String nickname);
    void updateNickname(Long userId, String newNickname);
}
