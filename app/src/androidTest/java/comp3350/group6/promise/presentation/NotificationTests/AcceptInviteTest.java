/*
 *
 * Acceptance test for "Respond to Project Invite" user story:
 * As a user I want to be able to accept or reject invites that
 * have been sent to me
 *
 */

package comp3350.group6.promise.presentation.NotificationTests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.group6.promise.R;
import comp3350.group6.promise.presentation.MainActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AcceptInviteTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void acceptInviteTest() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.loginEmailInput),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("teenytina@app.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.loginPasswordInput),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("123"), closeSoftKeyboard());

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.notifications), withContentDescription("Notifications"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.notifRecyclerView),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));

        ViewInteraction relativeLayout = onView(
                allOf(withId(R.id.notifItemContainer),
                        withParent(withParent(withId(R.id.notifRecyclerView))),
                        isDisplayed()));
        relativeLayout.check(matches(isDisplayed()));

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.notifRecyclerView),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                0)));
        recyclerView2.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction button = onView(
                allOf(withId(R.id.acceptInviteButton), withText("Accept"),
                        withParent(withParent(withId(R.id.dashboard_nav_fragment))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.rejectInviteButton), withText("Reject"),
                        withParent(withParent(withId(R.id.dashboard_nav_fragment))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.acceptInviteButton), withText("Accept"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dashboard_nav_fragment),
                                        0),
                                2),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction bottomNavigationItemView2 = onView(
                allOf(withId(R.id.home), withContentDescription("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav),
                                        0),
                                0),
                        isDisplayed()));
        bottomNavigationItemView2.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.project_container),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.projectRecyclerView),
                                        1),
                                0),
                        isDisplayed()));
        linearLayout.perform(click());
    }

    /*
     * Ideally you would want to confirm that the project information matches
     * that on the invite you accepted but the Espresso Recorder is not responding
     * to my clicks on the project title or content
     */

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
