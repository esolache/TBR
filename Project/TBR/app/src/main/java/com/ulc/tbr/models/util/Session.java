package com.ulc.tbr.models.util;

import java.io.Serializable;

import com.ulc.tbr.activities.MainActivity;
import com.ulc.tbr.databases.DatabaseHelper;


/*
This class is a model that is meant to represent the users of the application
* */
public class Session implements Serializable {
    private String studentID;
    private String tutorID;
    private String date;
    private String time;
    private String subject;
    private int courseNo;
    private String location;
    private String description;
    private int sessionID;


    public Session(String studentID, String tutorID, String date, String time, String subject,
                   int courseNo, String location, String description, int sessionID){

        this.studentID = studentID;
        this.tutorID = tutorID;
        this.date = date;
        this.time = time;
        this.subject = subject;
        this.courseNo = courseNo;
        this.location = location;
        this.description = description;
        this.sessionID = sessionID;
    }

    // Getters for the model
    public String getStudentID(){ return this.studentID; }
    public String getTutorID(){ return this.tutorID; }
    public int getSessionID(){ return this.sessionID; }
    public String getDate(){ return this.date;}
    public String getTime() { return time; }
    public String getSubject() { return subject; }
    public int getCourseNo() { return courseNo; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }

    // Setters for the model
    // This needs to update the database

    public String toString(){
        return date + " - " + time + ": " + subject + " " + courseNo;
    }

    public String sessionDetails(){
        return "Date: " + date+"\n"+"Time: " + time+"\n"+"Tutor ID: " + tutorID+"\n"+
                "Student ID: " + studentID+"\n"+ "Course: " + subject + " " + courseNo +"\n"+
                "Location: " + location+"\n"+ "Description: " + description;
    }


    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setTutorID(String tutorID) {
        this.tutorID = tutorID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setCourseNo(int courseNo) {
        this.courseNo = courseNo;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }
}
