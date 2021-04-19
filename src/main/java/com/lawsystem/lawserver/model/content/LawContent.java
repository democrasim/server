package com.lawsystem.lawserver.model.content;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.LawType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class LawContent {
    @Id
    @GeneratedValue
    private long id;

    @Enumerated(EnumType.ORDINAL)
    private LawType type;

}
