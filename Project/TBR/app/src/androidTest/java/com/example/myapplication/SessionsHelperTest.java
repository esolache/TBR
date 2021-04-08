package com.example.myapplication;

import android.content.Context;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;


import com.ulc.tbr.databases.DatabaseHelper;
import com.ulc.tbr.models.util.Session;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 * Test
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class SessionsHelperTest {
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
    public void getTutorSession() {
        ArrayList<Session> sessionList = db.getTutorSession("1111111111");
        assertTrue(sessionList.get(0).toString().equals("03/28/2021 - 09:00: Mathematics 341"));
    }
    @Test
    public void getStudentSession() {
        ArrayList<Session> sessionList = db.getStudentSession("0000000000");
        assertTrue(sessionList.get(0).toString().equals("04/21/2021 - 10:30: Data Science and Engineering 204"));
    }

    // More Tests
}