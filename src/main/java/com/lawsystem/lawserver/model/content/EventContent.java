package com.lawsystem.lawserver.model.content;

import java.util.Date;

import org.springframework.data.geo.Point;

public class EventContent extends DescribedContent {


    private Date date;
    private Point location;
}
