package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CompleteAdapter extends RecyclerView.Adapter <CompleteAdapter.ViewHolder> {
    Context context;
    List<Task> tasks;

    public CompleteAdapter(FragmentActivity activity, List<Task> taskList) {
        this.context=activity;
        this.tasks=taskList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CompleteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompleteAdapter.ViewHolder holder, int position) {
        Task task=tasks.get(position);
        holder.description.setText(task.getDescrition());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            description = (itemView).findViewById(R.id.text_item);
        }
    }
}
