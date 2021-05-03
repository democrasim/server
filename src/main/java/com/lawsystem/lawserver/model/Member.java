package com.lawsystem.lawserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Nullable;

import java.util.Date;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Document
public class Member {

    @Id
    private String id;

    @NonNull
    private String name;
    @NonNull
    private String phone;

    @JsonIgnore
    @Nullable
    private String token;

    private boolean president;

    private boolean registered;

    @CreatedDate
    private Date joined;
}
