package com.example.springmongodbpocsecond.model.service;

import com.example.springmongodbpocsecond.model.entities.Profile;
import com.example.springmongodbpocsecond.model.repos.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepo;

    @Override
    public Optional<Profile> getProfileByUserId(String userId) {
        return profileRepo.findByUserId(userId);
    }

    @Override
    public Optional<Profile> getProfileByUsername(String username) {
        return profileRepo.findByUsername(prepareUsername(username));
    }

    @Override
    public String prepareUsername(String username) {
        return username.toLowerCase();
    }

    @Override
    public void createUserProfile(String userId, String username) {
        Profile profile = new Profile(userId, prepareUsername(username));
        profileRepo.save(profile);
    }
}
