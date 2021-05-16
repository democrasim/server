package com.lawsystem.lawserver.model.content;

import org.springframework.data.geo.Point;

import java.util.Date;

public class EventContent extends DescribedContent {


    private Date date;
    private Point location;
}
