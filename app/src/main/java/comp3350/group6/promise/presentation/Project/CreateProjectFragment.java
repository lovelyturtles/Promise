package comp3350.group6.promise.presentation.Project;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import comp3350.group6.promise.R;
import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.business.EmptyInputException;
import comp3350.group6.promise.objects.Access;
import comp3350.group6.promise.application.CurrentSession;
import comp3350.group6.promise.objects.Project;

public class CreateProjectFragment extends Fragment {

    EditText nameInputView;
    EditText descriptionInputView;
    Button submitButtonView;
    Toolbar toolbarView;

    NavController navController;

    public CreateProjectFragment() {
        super(R.layout.fragment_create_project);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // Obtain views

        navController = Navigation.findNavController(view);

        nameInputView = view.findViewById(R.id.project_name_input);
        submitButtonView = view.findViewById(R.id.create_project_button);
        descriptionInputView = view.findViewById(R.id.project_description_input);
        toolbarView = view.findViewById(R.id.toolbar);

        // Update layout behaviours

        submitButtonView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View submitButtonView ) { handleSubmit(); }
        });

        // Keep "next" keyboard action while allowing multiline text
        descriptionInputView.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        descriptionInputView.setRawInputType(InputType.TYPE_CLASS_TEXT);

        // Setup toolbar with navigation controller
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbarView, navController, appBarConfiguration);

    }

    private void handleSubmit() {
        String name = nameInputView.getText().toString();
        String description = descriptionInputView.getText().toString();

        // Return to projects page if creation is successful
        if(createProject(name, description)) {
            NavDirections action = CreateProjectFragmentDirections.createProjectSuccess();
            navController.navigate(action);
        }
    }

    private boolean createProject(String name, String description) {
        boolean wasSuccessful = false;

        try {
            // TODO: Consider refactoring service methods to deal with accesses implicitly
            Project newProject = Service.projects.insertProject(new Project(name, description));
            Access newAccess = new Access(newProject.getProjectID(), CurrentSession.currentUser.getUserID());
            Service.accesses.insertAccess(newAccess);
            wasSuccessful = true;
        }
        catch (EmptyInputException e) {
            // Users cannot add projects with no name
            Toast.makeText(getContext(), "You need a name for your project.", Toast.LENGTH_LONG).show();
        }

        return wasSuccessful;
    }
}