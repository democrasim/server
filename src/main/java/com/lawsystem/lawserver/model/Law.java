package com.lawsystem.lawserver.model;

import com.lawsystem.lawserver.model.content.LawContent;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@Entity
public class Law {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Member legislator;

    @OneToOne
    private LawContent content;

    @OneToMany(mappedBy = "law")
    private List<LawVote> votes;

    @Enumerated(EnumType.ORDINAL)
    private LawStatus status = LawStatus.UNDER_VOTE;

    @CreationTimestamp
    private Date timestamp;

    private boolean constitutional = false;

    private boolean anonymousLegislator = false;

    @Nullable
    private String fakeName;

}
