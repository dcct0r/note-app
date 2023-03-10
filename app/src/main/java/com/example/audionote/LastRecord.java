package com.example.audionote;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LastRecord {
    public String getLastRecord(long duration) {
        Date lastTimeRecord = new Date();

        long seconds = TimeUnit.MILLISECONDS.toSeconds(lastTimeRecord.getTime() - duration);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(lastTimeRecord.getTime() - duration);
        long hours = TimeUnit.MILLISECONDS.toHours(lastTimeRecord.getTime() - duration);
        long days = TimeUnit.MILLISECONDS.toDays(lastTimeRecord.getTime() - duration);

        if(seconds < 60) {
            return "just now";
        } else if (minutes == 1) {
            return "a minute ago";
        } else if (minutes > 1 && minutes < 60) {
            return minutes + " minutes ago";
        } else if (hours == 1) {
            return "an hour ago";
        } else if (hours > 1 && hours < 24) {
            return hours + " hours ago";
        } else if (days == 1) {
            return "a day ago";
        } else {
            return days + " days ago";
        }
    }
}
