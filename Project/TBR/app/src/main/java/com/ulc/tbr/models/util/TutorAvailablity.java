package com.ulc.tbr.models.util;

import java.io.Serializable;

import com.ulc.tbr.activities.MainActivity;
import com.ulc.tbr.databases.DatabaseHelper;



public class TutorAvailablity implements Serializable {

    private String tutorId;
    private String tutorName;
    private String date;
    private String time;
    private boolean booked;

    public TutorAvailablity(String tutorName, String tutorId, String date, String time, boolean booked
                            ) { //String course, int courseNo
        this.tutorName = tutorName;
        this.tutorId = tutorId;
        this.date = date;
        this.time = time;
        this.booked = booked;


    }

    public String getTutorName() { return this.tutorName; }
    public String getTutorId() { return tutorId; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public boolean isBooked() { return this.booked; }


    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }


    public void setDate(String date) {
        this.date = date;
    }


    public void setTime(String time) {
        this.time = time;
    }


    public String toString() {
        return "" + this.getTutorId() + " " + this.getDate() + " " + this.getTime() + " " + this.isBooked();
    }

    public String toStringTutorName() {
        return "" + this.getTutorName() + " " + this.getDate() + " " + this.getTime() + " " + this.isBooked();
    }

}
