Iteration 1 Worksheet
=====================

## Paying off technical debt
[//]: <Show two instances of your group paying off technical debt. For these two instances:Explain how your are paying off the technical debt.Show commits, links to lines in your commit where you paid off technical debt.Classify the debt, and justify why you chose that classification with 1-3 sentences.>


## SOLID
[//]: <Find a SOLID violation in the project of group with group number n-1 in the same section of the course as you (group 1 does group 16). Open an issue in their project with the violation, clearly explaining the SOLID violation - specifying the type, provide a link to that issue. Be sure your links in the issues are to specific commits (not to main, or develop as those will be changed).Provide a link to the issue you created here.>


## Retrospective
Merging into the iteration2 branch (develop branch) more often has changed our group to communicate more and resolve problems faster. This also helped in speeding up the merging, as there were less conflict in code. When merging into the develop branch, we decided to announce that we have created a merge request or we have approved the merge request. This helped our group avoid problems with version control that we faced in the first iteration.

[//]: <Describe how the retrospective has changed the way you are doing your project. Is there evidence of the change in estimating/committing/peer review/timelines/testing? Provide those links and evidence here - or explain why there is not evidence. ADD evidence! - dani>


## Design patterns
We used the Adapter design pattern for our project. Since we wanted to use card views for each project and task item, we had to create an Adapter class to display each object in an appropriate format.
 * [ProjectAdapter](https://code.cs.umanitoba.ca/winter-2022-a01/group-6/promise/-/blob/Iteration-1/app/src/main/java/comp3350/group6/promise/util/ProjectAdapter.java)
 * [TaskAdapter](https://code.cs.umanitoba.ca/winter-2022-a01/group-6/promise/-/blob/Iteration-1/app/src/main/java/comp3350/group6/promise/util/TaskAdapter.java)


## Iteration 1 Feedback fixes
> Currently, your presentation layer is populating the fake database.  It should be your logic layer that interacts with the database. You should be able to swap the database without changing *anything* in the presentation layer.  For iterations 2 & 3, make sure the presentation layer doesn't directly access the database.

[Link to Issue](https://code.cs.umanitoba.ca/winter-2022-a01/group-6/promise/-/issues/42)
We got this feedback because we were populating the FakeDB in our presentation layer, instead of the logic layer. We removed all FakeDB related code from our project, and persistence code in our presentation layers.

[//]:<Provide links to the commits where you fixed the issue.>