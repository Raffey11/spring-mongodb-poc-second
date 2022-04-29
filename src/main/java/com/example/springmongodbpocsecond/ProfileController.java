package com.example.springmongodbpocsecond;

import com.example.springmongodbpocsecond.model.ProfilePojo;
import com.example.springmongodbpocsecond.model.entities.Profile;
import com.example.springmongodbpocsecond.model.repos.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/v1.0")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileRepository profileRepo;

    @PostMapping("/save-user-profile")
    public void getUserProfile(@RequestBody ProfilePojo userProfile) {
        Profile profile = Profile.builder()
                .userId(userProfile.getUserId())
                .username(userProfile.getUsername())
                .usernames(userProfile.getUsernames())
                .identities(userProfile.getIdentities())
                .preferences(userProfile.getPreferences())
                .signupTimestamp(new Date())
                .build();

        profileRepo.save(profile);
    }
}
