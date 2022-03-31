package comp3350.group6.promise.presentation.Notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import comp3350.group6.promise.R;
import comp3350.group6.promise.application.CurrentSession;
import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.objects.Notification;
import comp3350.group6.promise.util.NotificationAdapter;

//All the notifications in the database are printed to the screen
public class NotificationsActivity extends AppCompatActivity {

    private ArrayList<Notification> notificationsList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_notifications );

        recyclerView = findViewById( R.id.notifRecyclerView );

        //Get all the notifications for the current user
        notificationsList = Service.notifications.getNotifs( CurrentSession.currentUser.getUserID() );

        setAdapter();
    }

    private void setAdapter() {

        //create instance of recycler adapter
        NotificationAdapter adapter = new NotificationAdapter( notificationsList );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setItemAnimator( new DefaultItemAnimator() );
        recyclerView.setAdapter( adapter );

    }

}