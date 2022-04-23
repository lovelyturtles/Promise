/*
 *
 * Acceptance test "Send Invite" user story:
 * As a user I want to send an invite to a project I created
 *
 */

package comp3350.group6.promise.presentation.NotificationTests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
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
public class SendInviteTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void sendInviteTest() {
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

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.loginPasswordInput), withText("123"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText3.perform(pressImeActionButton());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.signOutButton), withText("Sign In"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                4),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab), withContentDescription("Dashboard Add Action"),
                        childAtPosition(
                                allOf(withId(R.id.coordinator_layout),
                                        childAtPosition(
                                                withId(R.id.main_nav_fragment),
                                                0)),
                                2),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.project_name_input),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.core.widget.NestedScrollView")),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("My Fancy Project"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.project_description_input),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.core.widget.NestedScrollView")),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("My fancy new project"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.create_project_button), withText("Create Project"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.core.widget.NestedScrollView")),
                                        0),
                                4),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.project_container),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.projectRecyclerView),
                                        1),
                                0),
                        isDisplayed()));
        linearLayout.perform(click());

        ViewInteraction overflowMenuButton = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        1),
                                0),
                        isDisplayed()));
        overflowMenuButton.perform(click());

        ViewInteraction materialTextView = onView(
                allOf(withId(androidx.appcompat.R.id.title), withText("Invite Users"),
                        childAtPosition(
                                childAtPosition(
                                        withId(androidx.appcompat.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.recipientEmailLabel), withText("Email of the user you want to invite"),
                        withParent(withParent(withId(R.id.dashboard_nav_fragment))),
                        isDisplayed()));
        textView.check(matches(withText("Email of the user you want to invite")));

        ViewInteraction editText = onView(
                allOf(withId(R.id.recipientEmailHint), withText("Enter their email here"),
                        withParent(withParent(withId(R.id.dashboard_nav_fragment))),
                        isDisplayed()));
        editText.check(matches(withText("Enter their email here")));

        ViewInteraction button = onView(
                allOf(withId(R.id.sendInviteButton), withText("Send Invite"),
                        withParent(withParent(withId(R.id.dashboard_nav_fragment))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.recipientEmailHint),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dashboard_nav_fragment),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("lazerrazor@app.com"), closeSoftKeyboard());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.sendInviteButton), withText("Send Invite"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dashboard_nav_fragment),
                                        0),
                                2),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.successSentMessage), withText("Your invite was sent to Louise"),
                        withParent(withParent(withId(R.id.dashboard_nav_fragment))),
                        isDisplayed()));
        textView2.check(matches(withText("Your invite was sent to Louise")));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.goBackProject), withText("Back to project"),
                        withParent(withParent(withId(R.id.dashboard_nav_fragment))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.someoneElse), withText("Invite More"),
                        withParent(withParent(withId(R.id.dashboard_nav_fragment))),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.goBackProject), withText("Back to project"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dashboard_nav_fragment),
                                        0),
                                1),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.settings), withContentDescription("Settings"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav),
                                        0),
                                3),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.signOutButton), withText("Sign Out"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dashboard_nav_fragment),
                                        0),
                                0),
                        isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.loginEmailInput),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText7.perform(replaceText("lazerrazor@app.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.loginPasswordInput),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText8.perform(replaceText("123"), closeSoftKeyboard());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.signOutButton), withText("Sign In"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                4),
                        isDisplayed()));
        materialButton6.perform(click());

        ViewInteraction bottomNavigationItemView2 = onView(
                allOf(withId(R.id.notifications), withContentDescription("Notifications"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView2.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.notifRecyclerView),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.notifTextView), withText("Tina has invited you to work on \"My Fancy Project\""),
                        withParent(allOf(withId(R.id.notifItemContainer),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        textView3.check(matches(withText("Tina has invited you to work on \"My Fancy Project\"")));
    }

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
