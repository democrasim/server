package com.lawsystem.lawserver.model.content;

import com.lawsystem.lawserver.model.LawType;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

public class EventContent extends DescribedContent {


    private Date date;
    private Point location;
}
