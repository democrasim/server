package com.lawsystem.lawserver.model.content;

import com.lawsystem.lawserver.model.LawType;
import org.springframework.data.geo.Point;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class EventContent extends DescribedContent {

    public EventContent() {
        this.setType(LawType.EVENT);
    }

    private Date date;
    private Point location;
}
