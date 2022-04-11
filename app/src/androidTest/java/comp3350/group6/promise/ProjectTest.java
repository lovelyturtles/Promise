package comp3350.group6.promise;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.group6.promise.presentation.MainActivity;

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class ProjectTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void Viewpage() {
        // add the new course
        onView(withId(R.id.loginEmailInput)).perform(typeText("4050"));
        onView(withId(R.id.loginPasswordInput)).perform(typeText("Project Management"));
        onView(withId(R.id.signInButton)).perform(click());
        closeSoftKeyboard();

    }

}