package comp3350.group6.promise.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import comp3350.group6.promise.R;
import comp3350.group6.promise.objects.Task;
import de.hdodenhof.circleimageview.CircleImageView;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> { // custom adapter that hold ViewHolder type

    private Context context;
    private List<Task> taskList;
    private OnTaskClickListener onTaskClickListener;
    private OnTaskLongClickListener onTaskLongClickListener;
    private static final String TAG = "TaskAdapter";

    //new
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mImageNames = new ArrayList<>();


    //new
    public TaskAdapter(Context context, ArrayList<String> imageNames, ArrayList<String> images ) {
        mImageNames = imageNames;
        mImages = images;
        this.context = context;
    }

    public TaskAdapter(Context context, List<Task> taskList, OnTaskClickListener onTaskClickListener, OnTaskLongClickListener onTaskLongClickListener) {
        this.context = context;
        this.taskList = taskList;
        this.onTaskClickListener = onTaskClickListener;
        this.onTaskLongClickListener = onTaskLongClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_taskitem, parent, false);
        return new TaskAdapter.ViewHolder(view, onTaskClickListener, onTaskLongClickListener);
    }

    @Override
    public void onBindViewHolder(TaskAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {
        //new
        Glide.with(context).asBitmap().load(mImages.get(position)).into(viewHolder.image);
        viewHolder.imageName.setText(mImageNames.get(position));

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + mImageNames.get(position));
                Toast.makeText(context, mImageNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        Task task = taskList.get(position);
        viewHolder.taskTitle.setText(task.getTitle());
        viewHolder.taskDescription.setText(task.getDescription());
    }

    @Override
    public int getItemCount() {
        if (taskList != null) {
            return taskList.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private LinearLayout taskContainer;
        private TextView taskTitle;
        private TextView taskDescription;
        private OnTaskClickListener clickListener;
        private OnTaskLongClickListener longClickListener;

        // new
        private CircleImageView image;
        private TextView imageName;
        private RelativeLayout parentLayout;


        // remove

        private TextView titleOfTaskToCreate;
        private Button buttonCreateTask;


        public ViewHolder(View itemView, OnTaskClickListener onTaskClickListener, OnTaskLongClickListener onTaskLongClickListener) { // constructor
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

            //new
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }

        @Override
        public void onClick(View view) {
            clickListener.onTaskClick(getAbsoluteAdapterPosition());
            Toast.makeText(itemView.getContext(), "You just click the task item", Toast.LENGTH_LONG).show();
        }

        @Override
        public boolean onLongClick(View view) {
            longClickListener.onLongTaskClick(getAbsoluteAdapterPosition());
            Toast.makeText(itemView.getContext(), "You just long click the task item", Toast.LENGTH_LONG).show();
            return true;
        }

    }

    public interface OnTaskClickListener {
        void onTaskClick(int position);
    }

    public interface OnTaskLongClickListener {
        void onLongTaskClick(int position);
    }
}
