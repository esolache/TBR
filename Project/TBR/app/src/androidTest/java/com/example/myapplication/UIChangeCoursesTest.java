package com.example.myapplication;

import android.view.View;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.ulc.tbr.R;
import com.ulc.tbr.activities.MainActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;


@RunWith(AndroidJUnit4ClassRunner.class)
public class UIChangeCoursesTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    public void navigate(){
        Fragment g = new Fragment();
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction().add(R.id.fragment_login, g);
        onView(withId(R.id.net_ID)).perform(clearText(), typeText("testTutor"));
        onView(withId(R.id.password)).perform(clearText(), typeText("testTutor"));
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

        onData(is("Change Courses")).perform(click());
    }

    @Test
    public void tutor_should_get_from_login() {

        try {
            Fragment g = new Fragment();
            activityActivityTestRule.getActivity()
                    .getSupportFragmentManager().beginTransaction().add(R.id.fragment_login, g);
            onView(withId(R.id.net_ID)).perform(clearText(), typeText("testTutor"));
            onView(withId(R.id.password)).perform(clearText(), typeText("testTutor"));
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

            onData(is("Change Courses")).perform(click());
            onView(withId(R.id.textView_TBR)).check(matches((isDisplayed())));
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void stutor_should_get_from_login() {

        try {
            Fragment g = new Fragment();
            activityActivityTestRule.getActivity()
                    .getSupportFragmentManager().beginTransaction().add(R.id.fragment_login, g);
            onView(withId(R.id.net_ID)).perform(clearText(), typeText("testStutor"));
            onView(withId(R.id.password)).perform(clearText(), typeText("testStutor"));
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

            onData(is("Change Courses")).perform(click());
            onView(withId(R.id.textView_TBR)).check(matches((isDisplayed())));
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    public void student_should_not_get_from_login() {

        try {
            Fragment g = new Fragment();
            activityActivityTestRule.getActivity()
                    .getSupportFragmentManager().beginTransaction().add(R.id.fragment_login, g);
            onView(withId(R.id.net_ID)).perform(clearText(), typeText("testStudent"));
            onView(withId(R.id.password)).perform(clearText(), typeText("testStudent"));
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
            try {
                onData(is("Change Courses")).perform(click());
                fail();
            } catch (Exception e){

            }

        } catch(Exception e) {
            fail();
        }
    }
/*
    @Test
    public void tutor_add_course(){
        navigate();
        final ListView listViewAdd = (ListView) withId(R.id.listView_availableClasses);
        final ListView listViewRemove = (ListView) withId(R.id.listView_myClasses);
        assertNotNull("The list was not loaded", listViewAdd);
        try {
            String item = listViewAdd.getItemAtPosition(0).toString();
            getInstrumentation().runOnMainSync(new Runnable() {
                @Override
                public void run() {
                    listViewAdd.performItemClick(listViewAdd, 0, listViewAdd.getItemIdAtPosition(0));

                }
            });
            onView(withId(R.id.button_addTutorClasses)).perform(click());
            if(listViewRemove.getItemAtPosition(0).toString()!=item){
                fail();
            }
        } catch (Exception e){
            fail();
        }

    }



*/

}
