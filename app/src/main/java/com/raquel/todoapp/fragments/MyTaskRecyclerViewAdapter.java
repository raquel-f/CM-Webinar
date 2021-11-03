package com.raquel.todoapp.fragments;

import androidx.recyclerview.widget.RecyclerView;

import android.icu.text.SimpleDateFormat;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.raquel.todoapp.databinding.FragmentTaskBinding;
import com.raquel.todoapp.viewmodel.Task;

import java.util.List;
import java.util.Date;
import java.util.Locale;

/**
 *
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTaskRecyclerViewAdapter extends RecyclerView.Adapter<MyTaskRecyclerViewAdapter.ViewHolder> {

    private final List<Task> mValues;
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

    public MyTaskRecyclerViewAdapter(List<Task> tasks) {
        mValues = tasks;
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
    }

    @Override
    public int getItemCount() {
        if(mValues == null){
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