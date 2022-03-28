package comp3350.group6.promise.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import comp3350.group6.promise.R;
import comp3350.group6.promise.objects.Project;

/*
    This class is a custom adapter for the dashboard page.
    I allows creating a customized cardView based on the list of projects.
    Reference for Class: https://www.geeksforgeeks.org/cardview-using-recyclerview-in-android-with-example/
 */

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {

    private Context context;
    private List<Project> projectsList;
    private ViewHolder.OnProjectClickListener onProjectClickListener;

    public ProjectAdapter(Context context, List<Project> projectsList, ViewHolder.OnProjectClickListener onProjectClickListener){
        this.context = context;
        this.projectsList = projectsList;
        this.onProjectClickListener = onProjectClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list_item, parent, false);
        return new ViewHolder(view, onProjectClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Project project = projectsList.get(position);
        if(project != null) {
            holder.nameView.setText(project.getProjectName());
            holder.descriptionView.setText(project.getStatement());

            // [iteration 2] change to proper number + add project image
            holder.imageView.setImageResource(R.drawable.astro);
            holder.memberCountView.setText("3");
            holder.taskCountView.setText("5");
        }
    }

    @Override
    public int getItemCount() {
        return projectsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private LinearLayout containerView;
        private TextView nameView;
        private TextView descriptionView;
        private TextView memberCountView;
        private TextView taskCountView;
        private ImageView imageView;
        private OnProjectClickListener listener;

        public ViewHolder(View itemView, OnProjectClickListener listener){
            super(itemView);

            containerView = itemView.findViewById(R.id.project_list_item_container);
            nameView = itemView.findViewById(R.id.project_name_input);
            descriptionView = itemView.findViewById(R.id.projectDescription);
            memberCountView = itemView.findViewById(R.id.memberCount);
            taskCountView = itemView.findViewById(R.id.taskCount);
            imageView = itemView.findViewById(R.id.project_image);

            this.listener = listener;
            containerView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onProjectClick(getAbsoluteAdapterPosition());
        }

        public interface OnProjectClickListener {
            void onProjectClick(int position);
        }
    }
}
