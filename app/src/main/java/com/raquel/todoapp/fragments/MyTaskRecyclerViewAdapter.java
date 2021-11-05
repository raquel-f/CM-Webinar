package com.raquel.todoapp.fragments;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.icu.text.SimpleDateFormat;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.raquel.todoapp.FragmentSwitcher;
import com.raquel.todoapp.databinding.FragmentTaskBinding;
import com.raquel.todoapp.viewmodel.Task;

import java.util.List;
import java.util.Date;
import java.util.Locale;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTaskRecyclerViewAdapter extends RecyclerView.Adapter<MyTaskRecyclerViewAdapter.ViewHolder> {

    private final List<Task> mValues;
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
    private FragmentSwitcher fragmentSwitcher;

    public MyTaskRecyclerViewAdapter(List<Task> tasks, FragmentSwitcher fragmentSwitcher) {
        mValues = tasks;
        this.fragmentSwitcher = fragmentSwitcher;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentTaskBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).getTitle());
        holder.mDateView.setText(dateFormat.format(mValues.get(position).getEndDate()));

        //add click listener to each list item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Task "+holder.getAbsoluteAdapterPosition());
            }
        });

        // TODO set hold listener
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                fragmentSwitcher.switchEditTask(holder.mItem);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mValues == null) {
            return 0;
        }
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTitleView;
        public final TextView mDateView;
        public Task mItem;

        public ViewHolder(FragmentTaskBinding binding) {
            super(binding.getRoot());
            mTitleView = binding.taskTitle;
            mDateView = binding.taskDate;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDateView.getText() + "'";
        }
    }
}