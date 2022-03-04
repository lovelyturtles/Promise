package comp3350.group6.promise.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import comp3350.group6.promise.R;
import comp3350.group6.promise.objects.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Task> taskList;

    public TaskAdapter(Context context, ArrayList<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskAdapter.ViewHolder viewHolder, int position) {
        Task task = taskList.get(position);

        viewHolder.taskTitle.setText(task.getTitle());
        viewHolder.taskDescription.setText(task.getDescription());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView taskTitle;
        private TextView taskDescription;

        public ViewHolder(View itemView) {
            super(itemView);

            this.taskTitle = itemView.findViewById(R.id.task_title);
            this.taskDescription = itemView.findViewById(R.id.task_description);
        }
    }

}
