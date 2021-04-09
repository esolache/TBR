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
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class UIGetATutorTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    public void navigate(){
        Fragment g = new Fragment();
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction().add(R.id.fragment_login, g);
        onView(withId(R.id.net_ID)).perform(clearText(), typeText("student"));
        onView(withId(R.id.password)).perform(clearText(), typeText("student"));
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.textView_TBR)).check(matches((isDisplayed())));

        onView(withId(R.id.spinner_homeMenu)).check(matches(allOf(isEnabled(), isClickable())))
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

        onData(is("Get A Tutor")).perform(click());
    }

    @Test
    public void student_should_get_from_login() {

        try {
            Fragment g = new Fragment();
            activityActivityTestRule.getActivity()
                    .getSupportFragmentManager().beginTransaction().add(R.id.fragment_login, g);
            onView(withId(R.id.net_ID)).perform(clearText(), typeText("student"));
            onView(withId(R.id.password)).perform(clearText(), typeText("student"));
            onView(withId(R.id.login)).perform(click());
            onView(withId(R.id.textView_TBR)).check(matches((isDisplayed())));

            onView(withId(R.id.spinner_homeMenu)).check(matches(allOf(isEnabled(), isClickable())))
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

            onData(is("Get A Tutor")).perform(click());
            onView(withId(R.id.textView_get_a_tutor)).check(matches((isDisplayed())));
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void stutor_should_get_from_login(){
        try {
            Fragment g = new Fragment();
            activityActivityTestRule.getActivity()
                    .getSupportFragmentManager().beginTransaction().add(R.id.fragment_login, g);
            onView(withId(R.id.net_ID)).perform(clearText(), typeText("stutor"));
            onView(withId(R.id.password)).perform(clearText(), typeText("stutor"));
            onView(withId(R.id.login)).perform(click());
            onView(withId(R.id.textView_TBR)).check(matches((isDisplayed())));

            onView(withId(R.id.spinner_homeMenu)).check(matches(allOf(isEnabled(), isClickable())))
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

            onData(is("Get A Tutor")).perform(click());
            onView(withId(R.id.textView_get_a_tutor)).check(matches((isDisplayed())));
        } catch (Exception e){
            fail();
        }
    }

    @Test
    public void tutor_should_not_get_from_login(){
        try {
            Fragment g = new Fragment();
            activityActivityTestRule.getActivity()
                    .getSupportFragmentManager().beginTransaction().add(R.id.fragment_login, g);
            onView(withId(R.id.net_ID)).perform(clearText(), typeText("tutor1"));
            onView(withId(R.id.password)).perform(clearText(), typeText("tutor1"));
            onView(withId(R.id.login)).perform(click());
            onView(withId(R.id.textView_TBR)).check(matches((isDisplayed())));

            onView(withId(R.id.spinner_homeMenu)).check(matches(allOf(isEnabled(), isClickable())))
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

            onData(is("Get A Tutor")).perform(click());
            onView(withId(R.id.textView_get_a_tutor)).check(matches((isDisplayed())));
            fail();
        } catch (Exception e){

        }
    }

    @Test
    public void buttons_work() {
        navigate();
        onView(withId(R.id.button_prevWeek)).perform(click());

    }
}
