package com.example.myapplication;

import com.ulc.tbr.*;
import androidx.fragment.app.Fragment;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.ulc.tbr.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class UIHomeTest {
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    // Makes sure that home fragment is displayed correctly
    public void should_display_home(){
        Fragment g = new Fragment();
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction().add(R.id.fragment_home,g);

    }

//    @Test
//    // Makes sure that home fragment is displayed correctly
//    public void should_display_home(){
//        Fragment g = new Fragment();
//        activityActivityTestRule.getActivity()
//                .getSupportFragmentManager().beginTransaction().add(R.id.fragment_home,g);
//
//    }

}

