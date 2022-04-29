package com.example.springmongodbpocsecond.model.repos;

import com.example.springmongodbpocsecond.model.entities.Profile;
import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ProfileRepository extends MongoRepository<Profile, String> {
    Optional<Profile> findByUserId(String userId);

    Optional<Profile> findByUsername(String username);

    @Query("{'identities.provider': ?0, 'identities.providerUserId': ?1 }")
    Optional<Profile> findByProviderAndProviderUserId(String provider, String providerUserId);

    @ExistsQuery("{'identities.provider': ?0, 'identities.providerUserId': ?1 }")
    boolean existsByProviderAndProviderUserId(String provider, String providerUserId);

    @ExistsQuery("{'usernames': ?0}")
    boolean existsProfileByUsername(String username);
}
