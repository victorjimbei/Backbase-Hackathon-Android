package com.vjimbei.backbase_hackathon_android.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vjimbei.backbase_hackathon_android.R;
import com.vjimbei.backbase_hackathon_android.entity.Task;
import com.vjimbei.backbase_hackathon_android.ui.utils.ApplicationPreferences;
import com.vjimbei.backbase_hackathon_android.ui.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {

    private List<Task> taskList;
    private LayoutInflater inflater;
    private OnTaskClicked onCLickListener;

    public interface OnTaskClicked {
        void onClickedTask(Task task);
    }

    public TasksAdapter(Context context, OnTaskClicked onTaskClicked) {
        taskList = new ArrayList<>();
        onCLickListener = onTaskClicked;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setList(List<Task> list) {
        taskList.clear();
        taskList.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        taskList.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_task, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Task task = getItem(position);
        holder.bindData(task);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCLickListener.onClickedTask(task);
            }
        });
    }

    private Task getItem(int position) {
        return taskList.get(position);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        private TextView title;
        private TextView description;
        private ProgressBar progress;
        private TextView status;
        private TextView revenue;
        private TextView milestone;
        private DateUtils dateUtils;
        private ApplicationPreferences applicationPreferences;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            dateUtils = new DateUtils();
            applicationPreferences = new ApplicationPreferences(context);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            progress = (ProgressBar) itemView.findViewById(R.id.progress);
            status = (TextView) itemView.findViewById(R.id.status);
            revenue = (TextView) itemView.findViewById(R.id.revenue);
            milestone = (TextView) itemView.findViewById(R.id.milestone);
        }

        public void bindData(Task task) {
            title.setText(task.getTitle());
            description.setText(task.getDescription());
            progress.setMax((int) task.getMilestoneLimit());
            progress.setProgress((int) task.getCurrentMilestoneValue());
            status.setText(task.getStatus());
            revenue.setText(String.format(context.getString(R.string.format_revenue), task.getRevenue()));
            milestone.setText(String.format(context.getString(R.string.format_milestone), task
                    .getCurrentMilestoneValue(), task.getMilestoneUnits()));
        }
    }
}
