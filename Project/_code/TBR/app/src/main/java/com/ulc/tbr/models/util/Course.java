package com.ulc.tbr.models.util;

import java.io.Serializable;

import com.ulc.tbr.activities.MainActivity;
import com.ulc.tbr.databases.DatabaseHelper;


public class Course implements Serializable {
    private String subject;
    private String course;
    private int courseNo;
    private boolean isActive;

    public Course(String subject, String course, int courseNo) {
        this.subject = subject;
        this.course = course;
        this.courseNo = courseNo;
        this.isActive = true;
    }

    public String getSubject() { return subject; }

    public String getCourse() { return course; }

    public int getCourseNo() { return courseNo; }

    public boolean isActive() { return isActive; }


    public String toString(){
        return course + " " + Integer.toString(courseNo);
    }

    public String toStringSubjectCourseNo(){ return  subject + " " + Integer.toString(courseNo); }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setCourseNo(int courseNo) {
        this.courseNo = courseNo;
    }

    public void setActive(boolean active) { isActive = active; }
}
