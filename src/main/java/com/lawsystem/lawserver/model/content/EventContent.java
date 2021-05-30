package com.lawsystem.lawserver.model.content;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.geo.Point;

public class EventContent extends DescribedContent {

    private Date date;
    private Point location;

    @Override
    public String toString() {

        return super.toString() + "\nDate: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date) + "\nLocation: " + location;
    }
}
