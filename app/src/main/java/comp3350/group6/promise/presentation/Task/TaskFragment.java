package comp3350.group6.promise.presentation.Task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import comp3350.group6.promise.R;
import comp3350.group6.promise.business.HandleService;
import comp3350.group6.promise.business.TaskService;
import comp3350.group6.promise.objects.AccountUser;
import comp3350.group6.promise.objects.Task;
import comp3350.group6.promise.presentation.User.AccountUserAdapter;

public class TaskFragment extends Fragment {

    private Task task;

    private Toolbar toolbarView;
    private TextView descriptionView;
    private TextView priorityView;
    private TextView deadlineView;

    private List<AccountUser> assignees;
    private TextView assigneeTextContent;
    private RecyclerView assigneeRecycler;

    private NavController navController;

    public TaskFragment() {
        super(R.layout.fragment_task);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // Get task using ID passed to fragment

        int id = TaskFragmentArgs.fromBundle(getArguments()).getTaskId();

        if (id != -1) {
            task = TaskService.getInstance().getTask(id);
            assignees = HandleService.getInstance().getTaskAssignees(id);
        }

        // Get views from layout

        navController = NavHostFragment.findNavController(this);

        toolbarView = view.findViewById(R.id.toolbar);
        descriptionView = view.findViewById(R.id.task_page_description);
        priorityView = view.findViewById(R.id.task_page_priority);
        deadlineView = view.findViewById(R.id.task_page_deadline);
        assigneeRecycler = view.findViewById(R.id.task_assignee_recycler);
        assigneeTextContent = view.findViewById(R.id.task_assignee_add_message);

        // Update layout content with task data

        toolbarView.setTitle(task.getTitle());
        descriptionView.setText(task.getDescription());
        priorityView.setText("Priority: " + task.getPriority());
        deadlineView.setText("Deadline: " + task.getDeadline().toLocaleString());

        // Set up assignee section

        if(assignees.size() > 0) {
            assigneeTextContent.setVisibility(View.GONE);
            assigneeRecycler.setVisibility(View.VISIBLE);
        }
        else {
            assigneeTextContent.setVisibility(View.VISIBLE);
            assigneeRecycler.setVisibility(View.GONE);
        }

        AccountUserAdapter assigneeListAdapter = new AccountUserAdapter(
                getContext(),
                assignees,
                assigneeView -> { handleAssigneeClick(); },
                R.layout.user_list_section_item
        );

        assigneeRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        assigneeRecycler.setAdapter(assigneeListAdapter);

        // Set up toolbar

        initializeToolbar();

    }

    private void handleAssigneeClick() {
        Toast.makeText(getActivity(), "Pressed Task Assignee", Toast.LENGTH_SHORT).show();
    }

    // Toolbar Methods

    private void initializeToolbar() {
        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        setHasOptionsMenu(true);
        activity.setTitle(task.getTitle());
        activity.setSupportActionBar(toolbarView);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbarView, navController, appBarConfiguration);
        NavigationUI.setupActionBarWithNavController(activity, navController, appBarConfiguration);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.task_toolbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
            case R.id.action_edit:
                // TODO: Implement action handler for task editing
                Toast.makeText(getContext(), "Pressed Edit Task", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}