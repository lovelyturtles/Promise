package comp3350.group6.promise.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import comp3350.group6.promise.R;
import comp3350.group6.promise.objects.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private Context context;
    private List<Task> taskList;
    private TaskAdapter.ViewHolder.OnTaskClickListener onTaskClickListener;

    public TaskAdapter(Context context, List<Task> taskList, TaskAdapter.ViewHolder.OnTaskClickListener onTaskClickListener) {
        this.context = context;
        this.taskList = taskList;
        this.onTaskClickListener = onTaskClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskAdapter.ViewHolder(view, this.onTaskClickListener);
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

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LinearLayout taskContainer;
        private TextView taskTitle;
        private TextView taskDescription;
        private OnTaskClickListener listener;

        public ViewHolder(View itemView, OnTaskClickListener listener) {
            super(itemView);

            this.taskContainer = itemView.findViewById(R.id.task_container);
            this.taskTitle = itemView.findViewById(R.id.task_title);
            this.taskDescription = itemView.findViewById(R.id.task_description);

            this.listener = listener;
            taskContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onTaskClick(getAbsoluteAdapterPosition());
        }

        public interface OnTaskClickListener {
            void onTaskClick(int position);
        }
    }

}
