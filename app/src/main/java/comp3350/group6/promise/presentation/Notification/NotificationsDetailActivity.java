package comp3350.group6.promise.presentation.Notification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import comp3350.group6.promise.R;
import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.objects.Notification;
import comp3350.group6.promise.objects.enumClasses.NotifType;

public class NotificationsDetailActivity extends AppCompatActivity {

    private TextView messageText;
    String viewMessage = "Something went wrong"; //Default message
    private Button acceptButton;
    private Button rejectButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_detail);
        messageText = findViewById(R.id.notifDetailMessage);
        acceptButton = findViewById(R.id.acceptInviteButton);
        rejectButton = findViewById(R.id.rejectInviteButton);

        Bundle extras = getIntent().getExtras();
        if( extras != null ) {
            int senderID = extras.getInt("senderID");
            int projectID = extras.getInt("projectID");
            int recipientID = extras.getInt("recipientID");
            handleInvite( senderID, projectID, recipientID );
        }


    }

    private void handleInvite( int senderID, int projectID, int recipientID ){

        String senderName = Service.accountUser.getUserByAccountID( senderID ).getUserName();
        String projectName = Service.projects.getProjectByID( projectID ).getProjectName();

        viewMessage = senderName + " has invited you to work on \"" + projectName + "\"";

        messageText.setText( viewMessage );

        Notification notification = new Notification( senderID, projectID, recipientID, NotifType.INVITE );

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Service.notifications.accept( notification );
                finish();
            }
        });

        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Service.notifications.reject( notification );
                finish();
            }
        });

    }

}