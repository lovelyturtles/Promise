package comp3350.group6.promise.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import comp3350.group6.promise.R;
import comp3350.group6.promise.objects.Project;

// Reference for Class: https://www.geeksforgeeks.org/cardview-using-recyclerview-in-android-with-example/
public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.Viewholder>{

    private Context context;
    private ArrayList<Project> projectsList;

    public ProjectAdapter(Context context, ArrayList<Project> projectsList){
        this.context = context;
        this.projectsList = projectsList;
    }

    @NonNull
    @Override
    public ProjectAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectAdapter.Viewholder holder, int position){
        Project project = projectsList.get(position);
        holder.projectNameTV.setText(project.getProjectName());
        holder.projectDescTV.setText(project.getStatement());

        //TODO: change to proper number + add project image
        holder.membersTV.setText("3");
        holder.boxTV.setText("5");
    }

    @Override
    public int getItemCount() {
        return projectsList.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView projectNameTV;
        private TextView projectDescTV;
        private TextView membersTV;
        private TextView boxTV;

        public Viewholder(@NonNull View itemView){
            super(itemView);
            projectNameTV = itemView.findViewById(R.id.projectName);
            projectDescTV = itemView.findViewById(R.id.projectDescription);
            membersTV = itemView.findViewById(R.id.memberNumber);
            boxTV = itemView.findViewById(R.id.boxNumber);
        }
    }
}
