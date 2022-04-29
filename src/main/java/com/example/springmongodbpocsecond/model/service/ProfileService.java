package com.example.springmongodbpocsecond.model.service;

import com.example.springmongodbpocsecond.model.entities.Profile;

import java.util.Optional;

public interface ProfileService {

    Optional<Profile> getProfileByUserId(String userId);

    Optional<Profile> getProfileByUsername(String username);

    String prepareUsername(String username);

    void createUserProfile(String userId, String username);
}
