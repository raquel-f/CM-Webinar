package com.raquel.todoapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raquel.todoapp.R;
import com.raquel.todoapp.viewmodel.Task;


public class EditTask extends Fragment {

    private static final String TASK_KEY = "task_key";
    private Task task;

    public EditTask() {
        // Required empty public constructor
    }

    public static EditTask newInstance(Task task) {
        EditTask fragment = new EditTask();
        Bundle args = new Bundle();
        args.putSerializable(TASK_KEY, task);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            task = (Task) getArguments().getSerializable(TASK_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit_task, container, false);




        return v;
    }
}