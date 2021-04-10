package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.ulc.tbr.R;
import com.ulc.tbr.activities.MainActivity;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class UISetATutorAvailable {
    //    @Rule
//    public ActivityScenarioRule<MainActivity> activityActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void tutorAvailable(){
        Fragment g = new Fragment();
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction().add(R.id.fragment_login,g);
        onView(withId(R.id.net_ID)).perform(clearText(),typeText("tutor4"));
        onView(withId(R.id.password)).perform(clearText(),typeText("tutor4"));
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.textView_TBR)).check(matches((isDisplayed())));

        onView(withId(R.id.spinner_homeMenu)).check(matches(allOf( isEnabled(), isClickable())))
                .perform(new ViewAction() {
                    @Override
                    public Matcher<View> getConstraints() {
                        return ViewMatchers.isEnabled(); // no constraints, they are checked above
                    }
                    @Override
                    public String getDescription() {
                        return "click button";
                    }
                    @Override
                    public void perform(UiController uiController, View view) {
                        view.performClick();
                    }
                });
        onData(is("Change Availability")).perform(click());
        onView(withId(R.id.calendar)).check(matches((isDisplayed())));

        onData(anything()).inAdapterView(withId(R.id.calendar)).atPosition(0).perform(click());

        onView(withId(R.id.selectWeek)).check(matches(allOf( isEnabled(), isClickable())))
                .perform(new ViewAction() {
                    @Override
                    public Matcher<View> getConstraints() {
                        return ViewMatchers.isEnabled(); // no constraints, they are checked above
                    }
                    @Override
                    public String getDescription() {
                        return "click button";
                    }
                    @Override
                    public void perform(UiController uiController, View view) {
                        view.performClick();
                    }
                });
        onData(is("01/24 - 01/30")).perform(click());

        onData(anything()).inAdapterView(withId(R.id.calendar)).atPosition(2).perform(click());
        onView((withId(R.id.confirm_availability))).perform(click());

    }

    @Test
    public void tutorCancelAvailable(){
        Fragment g = new Fragment();
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction().add(R.id.fragment_login,g);
        onView(withId(R.id.net_ID)).perform(clearText(),typeText("testTutor"));
        onView(withId(R.id.password)).perform(clearText(),typeText("testTutor"));
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.textView_TBR)).check(matches((isDisplayed())));

        onView(withId(R.id.spinner_homeMenu)).check(matches(allOf( isEnabled(), isClickable())))
                .perform(new ViewAction() {
                    @Override
                    public Matcher<View> getConstraints() {
                        return ViewMatchers.isEnabled(); // no constraints, they are checked above
                    }
                    @Override
                    public String getDescription() {
                        return "click button";
                    }
                    @Override
                    public void perform(UiController uiController, View view) {
                        view.performClick();
                    }
                });
        onData(is("Change Availability")).perform(click());
        onView(withId(R.id.calendar)).check(matches((isDisplayed())));

        onData(anything()).inAdapterView(withId(R.id.calendar)).atPosition(0).perform(click());

        onView(withId(R.id.selectWeek)).check(matches(allOf( isEnabled(), isClickable())))
                .perform(new ViewAction() {
                    @Override
                    public Matcher<View> getConstraints() {
                        return ViewMatchers.isEnabled(); // no constraints, they are checked above
                    }
                    @Override
                    public String getDescription() {
                        return "click button";
                    }
                    @Override
                    public void perform(UiController uiController, View view) {
                        view.performClick();
                    }
                });
        onData(is("01/24 - 01/30")).perform(click());

        onData(anything()).inAdapterView(withId(R.id.calendar)).atPosition(2).perform(click());
        onData(anything()).inAdapterView(withId(R.id.calendar)).atPosition(3).perform(click());
        onView((withId(R.id.confirm_availability))).perform(click());
    }


    @Test
    public void tutorCourses(){
        Fragment g = new Fragment();
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction().add(R.id.fragment_login,g);
        onView(withId(R.id.net_ID)).perform(clearText(),typeText("tutor4"));
        onView(withId(R.id.password)).perform(clearText(),typeText("tutor4"));
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.textView_TBR)).check(matches((isDisplayed())));

        onView(withId(R.id.spinner_homeMenu)).check(matches(allOf( isEnabled(), isClickable())))
                .perform(new ViewAction() {
                    @Override
                    public Matcher<View> getConstraints() {
                        return ViewMatchers.isEnabled(); // no constraints, they are checked above
                    }
                    @Override
                    public String getDescription() {
                        return "click button";
                    }
                    @Override
                    public void perform(UiController uiController, View view) {
                        view.performClick();
                    }
                });
        onData(is("Change Courses")).perform(click());

        onData(anything()).inAdapterView(withId(R.id.listView_availableClasses)).atPosition(0).perform(click());
        onView(withId(R.id.button_addTutorClasses)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView_myClasses)).atPosition(0).perform(click());
        onView(withId(R.id.button_removeTutorClasses)).perform(click());


    }


}