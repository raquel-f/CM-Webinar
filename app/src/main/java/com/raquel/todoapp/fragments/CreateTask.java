package com.raquel.todoapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.raquel.todoapp.FragmentSwitcher;
import com.raquel.todoapp.MainActivity;
import com.raquel.todoapp.R;
import com.raquel.todoapp.viewmodel.Task;
import com.raquel.todoapp.viewmodel.TaskViewModel;

import java.util.Calendar;
import java.util.Date;


public class CreateTask extends Fragment {

    private TaskViewModel viewModel;
    private FragmentSwitcher fragmentSwitcher;

    public CreateTask() {
        // Required empty public constructor
    }


    public static CreateTask newInstance() {
        return new CreateTask();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize the view model variable
        viewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);

        // initialize the interface
        if (getActivity() instanceof MainActivity) {
            fragmentSwitcher = (FragmentSwitcher) getActivity();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_task, container, false);

        // TODO: Add date picker

        // get the input widgets
        TextView title = v.findViewById(R.id.create_title_input);
        TextView desc = v.findViewById(R.id.create_desc_input);
        CalendarView date = v.findViewById(R.id.create_date_input);

        // TODO: Add end date variable

        // get the buttons
        Button cancelB = v.findViewById(R.id.create_cancel_button);
        Button createB = v.findViewById(R.id.create_create_button);
        // TODO: Add date button

        // TODO: Set date button listener

        // TODO: Update date listener
        // set calendar change listener
        date.setOnDateChangeListener((view, year, month, day) -> {
            Calendar c = Calendar.getInstance();
            c.set(year, month, day);
            long endTime = c.getTimeInMillis();
            date.setDate(endTime);
        });

        // set the button's listeners
        cancelB.setOnClickListener(view -> {
            // switch to list fragment
            fragmentSwitcher.switchTaskList();
        });

        createB.setOnClickListener(view -> {
            // get the input
            String newTitle = title.getText().toString();
            String newDesc = desc.getText().toString();
            Date newDate = new Date(date.getDate()); // TODO: Delete

            // create a new task
            Task newTask = new Task(newTitle, newDesc, newDate); // TODO: update

            // add task to view model
            viewModel.addTaskTodo(newTask);

            // switch to list fragment
            fragmentSwitcher.switchTaskList();
        });

        return v;
    }

}