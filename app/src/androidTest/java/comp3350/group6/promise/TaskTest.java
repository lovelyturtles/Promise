package comp3350.group6.promise;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.hamcrest.Matchers.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;


import com.android21buttons.fragmenttestrule.FragmentTestRule;

import comp3350.group6.promise.presentation.MainActivity;
import comp3350.group6.promise.presentation.Project.ProjectFragment;
import comp3350.group6.promise.presentation.Task.TaskFragment;

//@LargeTest
@RunWith(AndroidJUnit4.class)
public class TaskTest {

//    @Rule
//    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);
//
//    @Test
//    public void createTask() {
//        onView(withId(R.id.registerLink)).perform(click());
//    }


    @Rule
    public FragmentTestRule<?, TaskFragment> fragmentTestRule = FragmentTestRule.create(TaskFragment.class);


    @Test
    public void createTask(){
        onView(withId(R.id.toolbar)).perform(click());
    }
}
