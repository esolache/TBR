package com.ulc.tbr.models.users;

import java.io.Serializable;

import com.ulc.tbr.activities.MainActivity;
import com.ulc.tbr.databases.DatabaseHelper;


/*
This class is a model that is meant to represent the users of the application
* */
public class User implements Serializable {
    private String studentID;
    private String password;
    private String netID;
    private String email;
    private boolean tutor;
    private boolean tutee;


    public User(String studentID, String password, String netID, String email, boolean tutor, boolean tutee){
        this.studentID = studentID;
        this.password = password;
        this.netID = netID;
        this.email = email;
        this.tutor = tutor;
        this.tutee = tutee;
    }

    // Getters for the users
    public String getStudentID() { return studentID; }
    public String getPassword() { return password; }
    public String getNetID() { return netID; }
    public String getEmail() { return email; }
    public boolean isTutor() { return tutor; }
    public boolean isTutee() { return tutee; }

    // Setters for the users
    // Have to update the database

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNetID(String netID) {
        this.netID = netID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTutor(boolean tutor) {
        this.tutor = tutor;
    }

    public void setTutee(boolean tutee) {
        this.tutee = tutee;
    }
}
