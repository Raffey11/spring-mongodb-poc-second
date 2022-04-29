package com.example.springmongodbpocsecond.model.entities;

import com.example.springmongodbpocsecond.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
@CompoundIndexes(@CompoundIndex(
        name = "identity_index",
        def = "{'identities.provider' : 1, 'identities.providerUserId' : 1}"))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    public static final String PREFERENCES_KEY = "preferences";
    public static final String USER_KEY = "userId";

    @Id
    @JsonIgnore
    public String id;

    @Indexed(name = "user_id_index", unique = true)
    private String userId;

    private String username;

    @Indexed(name = "usernames_index", unique = true, sparse = true)
    private List<String> usernames;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT_ISO8601_SECONDS)
    private Date signupTimestamp;

    private Map<String, Object> preferences;

    @Singular
    private List<ProviderIdentity> identities;

    public Profile(String userId, Map<String, Object> preferences) {
        this.userId = userId;
        if (preferences == null) {
            this.preferences = new HashMap<>();
        } else {
            this.preferences = preferences;
        }
    }

    public Profile(String userId, String username) {
        this.userId = userId;
        this.username = username;
        this.usernames = buildList();
        this.usernames.add(username);
        this.preferences = new HashMap<>();
        this.signupTimestamp = new Date();
        this.identities = buildList();
    }

    private <T> ArrayList<T> buildList() {
        return new ArrayList<>();
    }

    public String getUsername() throws Exception {
        if (usernames.isEmpty()) {
            throw new Exception("Usernames not found");
        }

        return getLastUsernameFromUsernamesCollection();
    }

    @JsonProperty("username")
    public String getUsernameJsonValue() {
        return username;
    }

    private String getLastUsernameFromUsernamesCollection() {
        final int userNamesSize = usernames.size();
        final int userNamesLastElementIndex = userNamesSize - 1;

        return usernames.get(userNamesLastElementIndex);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProviderIdentity {
        private String provider;
        private String providerUserId;
    }
}
