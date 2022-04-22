What technical debt has been cleaned up
========================================

Show links to a commit where you paid off technical debt. Write 2-5 sentences
that explain what debt was paid, and what its classification is.

What technical debt did you leave?
==================================

What one item would you like to fix, and can't? Anything you write will not
be marked negatively. Classify this debt.

Discuss a Feature or User Story that was cut/re-prioritized
============================================

When did you change the priority of a Feature or User Story? Why was it
re-prioritized? Provide a link to the Feature or User Story. This can be from any
iteration.

Acceptance test/end-to-end
==========================
One of the tests we had was a test on the [Members](https://code.cs.umanitoba.ca/winter-2022-a01/group-6/promise/-/blob/iteration3/app/src/androidTest/java/comp3350/group6/promise/presentation/MemberTest.java) feature.
This feature required a long test because it covered almost all aspects of our application (accounts, projects, invitations) and we had to do multiple logins & logouts within a single test.
To make the test more isolated and avoid dependency issues, we created all the objects within the test rather than using default values in the database.

The flow of the test is as follows,
First, it creates three accounts (123, 456, and 789) to test on.
Then, it would create a project in 789's account and invite 123 and 456.
123 and 456 accepts the invitation, and will verify that they are a MEMBER to that project.
789 then gives 123 and ADMIN role, and 123 will verify that now it is an ADMIN of that project.

Acceptance test, untestable
===============
We had a hard time setting up the tests because we had some Android dependencies that would prevent us from using the Espresso framework. 
We did not have a lot of challenges with flaky acceptance tests because our application did not have non-deterministic components.
However, there were some elements that we were not able to `.check()` because of the way we constructed our UI. 
They were only testable by running the app, and not with automated assertions.

Velocity/teamwork
=================
Our estimations did not improve much through out the course. 

For the first iteration, we [underestimated](https://code.cs.umanitoba.ca/winter-2022-a01/group-6/promise/-/issues/36) the time that would take for each task. 
There were a lot of learnings to do on Android specific elements for the presentations layer and for the database.

In the second iteration, we had a mixture of [underestimation](https://code.cs.umanitoba.ca/winter-2022-a01/group-6/promise/-/issues/28) and [overestimation](https://code.cs.umanitoba.ca/winter-2022-a01/group-6/promise/-/issues/49).
The under/overestimation here was mostly on the UI layers, where the members who had more UI related code in the first iteration, tended to have an overestimation, and the members who had less code with UI components tended to underestimate the time.
Because we learned a lot from the first iteration and setup most of the things, we expected that the second iteration would be much smoother.
However, adding the real database and tests took us quite some time, resulting in an overall underestimation of the expected time.

For the third iteration, 