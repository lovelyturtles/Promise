package comp3350.group6.promise.presentation.User;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import comp3350.group6.promise.R;

public class LoginFragment extends Fragment {

    TextView registerTextView;
    NavController navController;

    public LoginFragment() {
        super(R.layout.fragment_landing);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // Get views from layout

        registerTextView = view.findViewById(R.id.registerLink);
        navController = Navigation.findNavController(view);

        // Update layout behaviours

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = LoginFragmentDirections.register();
                navController.navigate(action);
            }
        });

    }

}