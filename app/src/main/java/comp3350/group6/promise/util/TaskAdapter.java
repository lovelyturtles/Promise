package comp3350.group6.promise.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import comp3350.group6.promise.R;
import comp3350.group6.promise.objects.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private Context context;
    private List<Task> taskList;
    private OnTaskClickListener onTaskClickListener;
    private OnTaskLongClickListener onTaskLongClickListener;


    public TaskAdapter(Context context, List<Task> taskList, OnTaskClickListener onTaskClickListener, OnTaskLongClickListener onTaskLongClickListener) {
        this.context = context;
        this.taskList = taskList;
        this.onTaskClickListener = onTaskClickListener;
        this.onTaskLongClickListener = onTaskLongClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskAdapter.ViewHolder(view,onTaskClickListener,onTaskLongClickListener);
    }

    @Override
    public void onBindViewHolder(TaskAdapter.ViewHolder viewHolder, int position) {
        Task task = taskList.get(position);

        viewHolder.taskTitle.setText(task.getTitle());
        viewHolder.taskDescription.setText(task.getDescription());
    }

    @Override
    public int getItemCount() {
        if (taskList != null) {
            return taskList.size();
        }
        return -1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private LinearLayout taskContainer;
        private TextView taskTitle;
        private TextView taskDescription;
        private OnTaskClickListener clickListener;
        private OnTaskLongClickListener longClickListener;

        // remove

        private TextView titleOfTaskToCreate;
        private Button buttonCreateTask;


        public ViewHolder(View itemView, OnTaskClickListener onTaskClickListener, OnTaskLongClickListener onTaskLongClickListener ) { // constructor
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);


            this.taskContainer = itemView.findViewById(R.id.task_container);
            this.taskTitle = itemView.findViewById(R.id.task_title);
            this.taskDescription = itemView.findViewById(R.id.task_description);

            this.clickListener = onTaskClickListener;
            this.longClickListener = onTaskLongClickListener;

            this.titleOfTaskToCreate = itemView.findViewById(R.id.button_create_task);
            this.buttonCreateTask = itemView.findViewById(R.id.button_create_task);
        }

        @Override
        public void onClick(View view) {
            clickListener.onTaskClick(getAbsoluteAdapterPosition());
            Toast.makeText(itemView.getContext(),"You just click the task item",Toast.LENGTH_LONG).show();
        }

        @Override
        public boolean onLongClick(View view) {
            longClickListener.onLongTaskClick(getAbsoluteAdapterPosition());
            Toast.makeText(itemView.getContext(),"You just long click the task item", Toast.LENGTH_LONG).show();
            return true;
        }

    }

    public interface OnTaskClickListener {
        void onTaskClick(int position);
    }

    public interface OnTaskLongClickListener{
        void onLongTaskClick(int position);
    }
}
