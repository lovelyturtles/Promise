package comp3350.group6.promise.presentation.Task;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import comp3350.group6.promise.R;
import comp3350.group6.promise.objects.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> { // custom adapter that hold ViewHolder type

    private List<Task> taskList;
    private OnTaskClickListener onTaskClickListener;
    private OnTaskLongClickListener onTaskLongClickListener;

//    private ArrayList<String> mImages = new ArrayList<>();
//    private ArrayList<String> mImageNames = new ArrayList<>();
//
//    public TaskAdapter(Context context, ArrayList<String> imageNames, ArrayList<String> images ) {
//        mImageNames = imageNames;
//        mImages = images;
//        this.context = context;
//    }

    public TaskAdapter(Context context, List<Task> taskList, OnTaskClickListener onTaskClickListener, OnTaskLongClickListener onTaskLongClickListener) {
        this.taskList = taskList;
        this.onTaskClickListener = onTaskClickListener;
        this.onTaskLongClickListener = onTaskLongClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list_item, parent, false);
        return new ViewHolder(view, this.onTaskClickListener, this.onTaskLongClickListener);
    }

    @Override
    public void onBindViewHolder(TaskAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {

//        Glide.with(context).asBitmap().load(mImages.get(position)).into(viewHolder.image);
//        viewHolder.imageName.setText(mImageNames.get(position));
//
//        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG, "onClick: " + mImageNames.get(position));
//                Toast.makeText(context, mImageNames.get(position), Toast.LENGTH_SHORT).show();
//            }
//        });

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

//        private CircleImageView image;
//        private TextView imageName;
//        private RelativeLayout parentLayout;

        public ViewHolder(View itemView, OnTaskClickListener onTaskClickListener, OnTaskLongClickListener onTaskLongClickListener) { // constructor
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            this.taskContainer = itemView.findViewById(R.id.task_container);
            this.taskTitle = itemView.findViewById(R.id.task_title);
            this.taskDescription = itemView.findViewById(R.id.task_description);

            this.clickListener = onTaskClickListener;
            this.longClickListener = onTaskLongClickListener;

//            image = itemView.findViewById(R.id.project_image);
//            imageName = itemView.findViewById(R.id.image_name);
//            parentLayout = itemView.findViewById(R.id.parent_layout);

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
