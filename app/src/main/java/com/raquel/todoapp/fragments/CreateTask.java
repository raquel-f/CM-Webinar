package com.raquel.todoapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.raquel.todoapp.R;
import com.raquel.todoapp.viewmodel.Task;
import com.raquel.todoapp.viewmodel.TaskViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class CreateTask extends Fragment {

    private TaskViewModel viewModel;

    public CreateTask() {
        // Required empty public constructor
    }


    public static CreateTask newInstance(String param1, String param2) {
        return new CreateTask();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize the view model variable
        viewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);

        // initialize the interface
        // TODO implement

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_task, container, false);

        // get the input widgets
        TextView title = v.findViewById(R.id.create_title_input);
        TextView desc = v.findViewById(R.id.create_desc_input);
        CalendarView date = v.findViewById(R.id.create_date_input);

        // get the buttons
        Button cancelB = v.findViewById(R.id.create_cancel_button);
        Button createB = v.findViewById(R.id.create_create_button);

        // set the button's listeners
        cancelB.setOnClickListener(view -> {
            // switch to list fragment
            // TODO implement
        });

        createB.setOnClickListener(view -> {
            // get the input
            String newTitle = title.getText().toString();
            String newDesc = desc.getText().toString();
            Date newDate = new Date(date.getDate());

            // create a new task
            Task newTask = new Task(newTitle, newDesc, newDate);

            // add task to view model
            viewModel.addTaskTodo(newTask);

            // switch to list fragment
            // TODO implement
        });

        return v;
    }

}