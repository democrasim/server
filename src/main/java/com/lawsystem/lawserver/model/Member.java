package com.lawsystem.lawserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.Date;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String name;
    @NonNull
    private String phone;

    @JsonIgnore
    @Nullable
    private String token;

    private boolean president;

    private boolean registered;

    @CreationTimestamp
    private Date joined;
}
