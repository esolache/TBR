package com.example.myapplication;

import android.content.Context;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;


import com.ulc.tbr.models.util.Course;
import com.ulc.tbr.databases.DatabaseHelper;

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
public class CoursesHelperTest {
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
    public void searchSubjects() {
        ArrayList<String> subjects = db.getAllSubjects();
        assertTrue(subjects.get(0).equals("Computer Science"));
        assertTrue(subjects.get(1).equals("Electrical and Computer Engineering"));
    }
    @Test
    public void searchCourses() {
        ArrayList<Course> courses = db.getAllCoursesBySubject("Computer Science");
        assertTrue(courses.get(0).toString().equals("Programming 1 200"));
    }
    @Test
    public void getAllCourses() {
        ArrayList<Course> courses = db.getDataCourses();
        //Log.d("getAllCourses",courses.get(0).toString());
        // Log.d("getAllCourses 2",courses.get(0).toStringSubjectCourseNo());
        assertTrue(courses.get(0).toString().equals("Programming 1 200"));
        assertTrue(courses.get(0).toStringSubjectCourseNo().equals("Computer Science 200"));
    }
    @Test
    public void getTutorCourses() {
        ArrayList<Course> courses = db.getTutorCourses("1111111112");
        //Log.d("TutorCourse",courses.get(1).toStringSubjectCourseNo());
        assertTrue(courses.get(1).toStringSubjectCourseNo().equals("Electrical and Computer Engineering 204"));
    }
    @Test
    public void deleteTutorCourse() {
        db.deleteTutorCourse("1111111111","Mathematics",221);
        ArrayList<Course> courses = db.getTutorCourses("1111111111");
        assertTrue(courses.get(2).toStringSubjectCourseNo().equals("Computer Science 400"));
    }
    @Test
    public void getTutorsbyCourse() {
        ArrayList<String> courses = db.getAvailableCourseTutorIDs("Mathematics","222");
        //Log.d("TutorBC",courses.get(0));
        assertTrue(courses.get(0).equals("1111111112"));
    }
}
