package comp3350.group6.promise.presentation.Notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
    private NotificationAdapter.NotificationClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populatePage();
    }

    @Override
    public void onRestart(){
        super.onRestart();
        populatePage();
    }

    private void populatePage(){
        setContentView( R.layout.activity_notifications );
        recyclerView = findViewById( R.id.notifRecyclerView );
        //Get all the notifications for the current user
        notificationsList = Service.notifications.getNotifs( CurrentSession.currentUser.getUserID() );
        setAdapter();
    }

    private void setAdapter() {

        setOnClickListener();
        //create instance of recycler adapter
        NotificationAdapter adapter = new NotificationAdapter( notificationsList, listener );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setItemAnimator( new DefaultItemAnimator() );
        recyclerView.setAdapter( adapter );

    }

    private void setOnClickListener(){
        listener = new NotificationAdapter.NotificationClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent( getApplicationContext(), NotificationsDetailActivity.class );
                Bundle extras = new Bundle();
                extras.putInt( "senderID", notificationsList.get( position ).getSenderID() );
                extras.putInt( "projectID", notificationsList.get( position ).getProjectID() );
                extras.putInt( "recipientID", notificationsList.get( position ).getRecipientID() );
                intent.putExtras( extras );
                startActivity( intent );
            }
        };
    }

}