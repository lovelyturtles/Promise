package comp3350.group6.promise.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import comp3350.group6.promise.R;
import comp3350.group6.promise.business.AccessService;
import comp3350.group6.promise.objects.Project;

/*
    This class is a custom adapter for the dashboard page.
    I allows creating a customized cardView based on the list of projects.
    Reference for Class: https://www.geeksforgeeks.org/cardview-using-recyclerview-in-android-with-example/
 */

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.Viewholder> {

    private Context context;
    private List<Project> projectsList;
    private static AccessService accessService = new AccessService();
    private Viewholder.OnCardListener onCardListener;

    public ProjectAdapter(Context context, List<Project> projectsList, Viewholder.OnCardListener onCardListener){
        this.context = context;
        this.projectsList = projectsList;
        this.onCardListener = onCardListener;
    }

    @NonNull
    @Override
    public ProjectAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new Viewholder(view, onCardListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectAdapter.Viewholder holder, int position) {
        Project project = projectsList.get(position);
        if(project != null){
            int numUsers = 0;

            try {
                numUsers = accessService.getUsers(project.getProjectID()).size(); // get the number of users in project
            } catch (Exception e) {
                e.printStackTrace();
            }

            holder.projectNameTV.setText(project.getProjectName());
            holder.projectDescTV.setText(project.getStatement());
            holder.projectImg.setImageResource(R.drawable.astro);
            holder.membersTV.setText(Integer.toString(numUsers));
            holder.boxTV.setText("5"); //TODO: set to # of tasks
        }
    }

    @Override
    public int getItemCount() {
        return projectsList.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private CardView projectCard;
        private TextView projectNameTV;
        private TextView projectDescTV;
        private TextView membersTV;
        private TextView boxTV;
        private ImageView projectImg;
        private OnCardListener listener;

        public Viewholder(@NonNull View itemView, OnCardListener listener){
            super(itemView);

            projectCard =  itemView.findViewById(R.id.card_view);
            projectNameTV = itemView.findViewById(R.id.project_name);
            projectDescTV = itemView.findViewById(R.id.projectDescription);
            membersTV = itemView.findViewById(R.id.memberNumber);
            projectImg = itemView.findViewById(R.id.project_image);
            boxTV = itemView.findViewById(R.id.boxNumber);
            this.listener = listener;

            projectCard.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onCardClick(getAbsoluteAdapterPosition());
        }

        public interface OnCardListener{
            void onCardClick(int position);
        }
    }
}
