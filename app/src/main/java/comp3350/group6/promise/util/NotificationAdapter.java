package comp3350.group6.promise.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import comp3350.group6.promise.R;
import comp3350.group6.promise.application.Service;
import comp3350.group6.promise.objects.Notification;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotifViewHolder> {

    private final ArrayList<Notification> notificationsList;
    public NotificationAdapter( ArrayList<Notification> notificationsList ){
        this.notificationsList = notificationsList;
    }

    public static class NotifViewHolder extends RecyclerView.ViewHolder{

        private TextView notifText;
        public NotifViewHolder( final View view ){

            super( view );
            notifText = view.findViewById( R.id.notifTextView );

        }

    }

    @NonNull
    @Override
    public NotificationAdapter.NotifViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() ).inflate(R.layout.notif_item_design, parent, false );
        return new NotifViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.NotifViewHolder holder, int position) {

        int senderID = notificationsList.get( position ).getSenderID();
        String senderName = Service.accountUser.getUserByAccountID( senderID ).getUserName();

        int projectID = notificationsList.get( position ).getProjectID();
        String projectName = Service.projects.getProjectByID( projectID ).getProjectName();

        String notifMessage = senderName + "has invited you to work on " + projectName;

        holder.notifText.setText( notifMessage );
    }

    @Override
    public int getItemCount() {
        return notificationsList.size();
    }
}
