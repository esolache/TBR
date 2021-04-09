package com.example.myapplication;

import android.content.Context;
import android.util.Log;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;


import com.ulc.tbr.models.util.TutorAvailablity;
import com.ulc.tbr.databases.DatabaseHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 * Test
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class AvailabilityTest {
    private DatabaseHelper db;

    @Before
    public void createDb() {
        // Make sure DB is cleared out
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = new DatabaseHelper(appContext);
    }
    @After
    public void tearDown() throws Exception {
        db.close();
    }
    @Test
    public void checkAvailability() {
        ArrayList<TutorAvailablity> tutorAvailabilityList;
        tutorAvailabilityList = db.getTutorAvailabilityOnDate("1111111112","01/26/2021");
        Log.d("check",tutorAvailabilityList.get(0).toString());
        assertTrue(tutorAvailabilityList.get(0).toString().equals("1111111112 01/26/2021 04:00 false"));
    }
    @Test
    public void deleteAvailability() {
        db.deleteAvailability("1111111111","01/26/2021","10:00");
        ArrayList<TutorAvailablity> tutorAvailabilityList;
        tutorAvailabilityList = db.getTutorAvailabilityOnDate("1111111111","01/26/2021");
        assertTrue(tutorAvailabilityList.get(1).toString().equals("1111111111 01/26/2021 10:30 false"));
    }
    @Test
    public void modifyAvailability() {
        db.modifySessionIsAvailable("1111111111","01/26/2021","09:30",true);
        ArrayList<TutorAvailablity> tutorAvailabilityList;
        tutorAvailabilityList = db.getTutorAvailabilityOnDate("1111111111","01/26/2021");
        assertTrue(tutorAvailabilityList.get(0).toString().equals("1111111111 01/26/2021 09:30 true"));
    }
    @Test
    public void availabilityDateTimeCourse() {
        ArrayList<TutorAvailablity> tutorAvailabilityList;
        tutorAvailabilityList = db.getTutorAvailabilitiesOnDateAndTime("400","04/20/2021","09:00");
        Log.d("Size", String.valueOf(tutorAvailabilityList.size()));
        Log.d("Size", tutorAvailabilityList.get(0).toString());
        assertTrue(tutorAvailabilityList.get(0).toString().equals("1111111111 04/20/2021 09:00 false"));
    }
}