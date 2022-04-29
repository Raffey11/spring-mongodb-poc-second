package com.example.springmongodbpocsecond.model;

import com.example.springmongodbpocsecond.model.entities.Profile;
import com.example.springmongodbpocsecond.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProfilePojo {
    public String id;
    private String userId;
    private String username;
    private List<String> usernames;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT_ISO8601_SECONDS)
    private Date signupTimestamp;
    private Map<String, Object> preferences;
    @Singular
    private List<Profile.ProviderIdentity> identities;
}
