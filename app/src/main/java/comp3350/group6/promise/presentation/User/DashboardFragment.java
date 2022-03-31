package comp3350.group6.promise.presentation.User;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import comp3350.group6.promise.R;

public class DashboardFragment extends Fragment {

    NavController navController;
    FloatingActionButton fab;
    BottomNavigationView bottomNav;

    public DashboardFragment() {
        super(R.layout.fragment_dashboard);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // Obtain views from layout

        NavHostFragment navHost = (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.dashboard_nav_fragment);
        navController = navHost.getNavController();
        fab = view.findViewById(R.id.fab);
        bottomNav = view.findViewById(R.id.bottom_nav);

        // Update layout behaviours

        NavigationUI.setupWithNavController(bottomNav, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
                @Override
                public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle arguments) {
                    handleDestinationChange(arguments);
                }
            }
        );

    }

    private void handleDestinationChange(@Nullable Bundle arguments) {
        boolean showFloatingButton = false;
        if (arguments != null) {
            showFloatingButton = arguments.getBoolean("showFloatingButton", false);
        }
        if(showFloatingButton) {
            fab.setVisibility(View.VISIBLE);
        } else {
            fab.setVisibility(View.GONE);
        }
    }

}