package com.lawsystem.lawserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProsecutionAppeal {
    @Id
    private String id;
    @DBRef
    private Prosecution prosecution;
    private String reason;
    private ProsecutionStatus status;
}
