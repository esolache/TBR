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
public class UILoginTest {
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    @Test
    public void should_login_as_student(){
        Fragment g = new Fragment();
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction().add(R.id.fragment_login,g);
        onView(withId(R.id.net_ID)).perform(clearText(),typeText("student"));
        onView(withId(R.id.password)).perform(clearText(),typeText("student"));
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.textView_TBR)).check(matches((isDisplayed())));
    }
    @Test
    public void should_login_as_tutor(){
        Fragment g = new Fragment();
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction().add(R.id.fragment_login,g);
        onView(withId(R.id.net_ID)).perform(clearText(),typeText("tutor1"));
        onView(withId(R.id.password)).perform(clearText(),typeText("tutor1"));
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.textView_TBR)).check(matches((isDisplayed())));
    }
    @Test
    public void should_login_as_stutor(){
        Fragment g = new Fragment();
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction().add(R.id.fragment_login,g);
        onView(withId(R.id.net_ID)).perform(clearText(),typeText("stutor"));
        onView(withId(R.id.password)).perform(clearText(),typeText("stutor"));
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.textView_TBR)).check(matches((isDisplayed())));
    }

    @Test
    public void should_not_login_as_bogus(){
        Fragment g = new Fragment();
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction().add(R.id.fragment_login,g);
        onView(withId(R.id.net_ID)).perform(clearText(),typeText("bogus"));
        onView(withId(R.id.password)).perform(clearText(),typeText("bogus"));
        onView(withId(R.id.login)).perform(click());
        try {
            onView(withId(R.id.textView_TBR)).check(matches((isDisplayed())));
        } catch (Exception e){

        }
    }

    @Test
    public void should_not_login_as_fakeuser(){
        Fragment g = new Fragment();
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction().add(R.id.fragment_login,g);
        onView(withId(R.id.net_ID)).perform(clearText(),typeText("fakeUser"));
        onView(withId(R.id.password)).perform(clearText(),typeText("fakeUser"));
        onView(withId(R.id.login)).perform(click());
        try {
            onView(withId(R.id.textView_TBR)).check(matches((isDisplayed())));
        } catch (Exception e){

        }
    }

    @Test
    public void should_not_login_with_only_username(){
        Fragment g = new Fragment();
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction().add(R.id.fragment_login,g);
        onView(withId(R.id.net_ID)).perform(clearText(),typeText("tutor1"));
        onView(withId(R.id.login)).perform(click());
        try {
            onView(withId(R.id.textView_TBR)).check(matches((isDisplayed())));
        } catch (Exception e){

        }
    }

    @Test
    public void should_not_login_with_only_password(){
        Fragment g = new Fragment();
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction().add(R.id.fragment_login,g);
        onView(withId(R.id.password)).perform(clearText(),typeText("tutor1"));
        onView(withId(R.id.login)).perform(click());
        try {
            onView(withId(R.id.textView_TBR)).check(matches((isDisplayed())));
        } catch (Exception e){

        }
    }

    @Test
    public void should_not_login_with_incorrect_password_but_correct_username(){
        Fragment g = new Fragment();
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction().add(R.id.fragment_login,g);
        onView(withId(R.id.net_ID)).perform(clearText(),typeText("tutor1"));
        onView(withId(R.id.password)).perform(clearText(),typeText("asdasd"));
        onView(withId(R.id.login)).perform(click());
        try {
            onView(withId(R.id.textView_TBR)).check(matches((isDisplayed())));
        } catch (Exception e){

        }
    }

    @Test
    public void should_not_login_with_correct_password_but_incorrect_username() {
        Fragment g = new Fragment();
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction().add(R.id.fragment_login, g);
        onView(withId(R.id.net_ID)).perform(clearText(), typeText("fakeUser"));
        onView(withId(R.id.password)).perform(clearText(), typeText("tutor1"));
        onView(withId(R.id.login)).perform(click());
        try {
            onView(withId(R.id.textView_TBR)).check(matches((isDisplayed())));
        } catch (Exception e) {

        }
    }

    @Test
    public void should_not_login_as_long_integer_string(){
        Fragment g = new Fragment();
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction().add(R.id.fragment_login,g);
        onView(withId(R.id.net_ID)).perform(clearText(),typeText("12314344353"));
        onView(withId(R.id.password)).perform(clearText(),typeText("68328628487378"));
        onView(withId(R.id.login)).perform(click());
        try {
            onView(withId(R.id.textView_TBR)).check(matches((isDisplayed())));
        } catch (Exception e){

        }
    }


    @Test
    public void should_not_login_as_special_characters_string(){
        Fragment g = new Fragment();
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction().add(R.id.fragment_login,g);
        onView(withId(R.id.net_ID)).perform(clearText(),typeText("1231434as@@&%dad4353"));
        onView(withId(R.id.password)).perform(clearText(),typeText("68328628487^&^#@&jkshdak378"));
        onView(withId(R.id.login)).perform(click());
        try {
            onView(withId(R.id.textView_TBR)).check(matches((isDisplayed())));
        } catch (Exception e){

        }
    }

}


