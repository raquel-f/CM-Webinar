package com.raquel.todoapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.raquel.todoapp.FragmentSwitcher;
import com.raquel.todoapp.MainActivity;
import com.raquel.todoapp.R;
import com.raquel.todoapp.viewmodel.Status;
import com.raquel.todoapp.viewmodel.Task;
import com.raquel.todoapp.viewmodel.TaskViewModel;

import java.util.Calendar;
import java.util.Date;


public class EditTask extends Fragment {

    private static final String TASK_KEY = "task_key";
    private Task task;

    private TaskViewModel viewModel;
    private FragmentSwitcher fragmentSwitcher;

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
        View v = inflater.inflate(R.layout.fragment_edit_task, container, false);

        // get the task information
        String title = task.getTitle();
        String desc = task.getDescription();
        Status status = task.getStatus();
        Date date = task.getEndDate();

        // TODO: Add date picker

        // get the input widgets
        TextView titleView = v.findViewById(R.id.edit_title_input);
        TextView descView = v.findViewById(R.id.edit_desc_input);
        RadioGroup radioGroup = v.findViewById(R.id.edit_status_input);
        RadioButton radioTODO = v.findViewById(R.id.edit_status_TODO);
        RadioButton radioDOING = v.findViewById(R.id.edit_status_DOING);
        RadioButton radioDONE = v.findViewById(R.id.edit_status_DONE);
        CalendarView calendarView = v.findViewById(R.id.edit_date_input); // TODO: Delete
        Button cancelB = v.findViewById(R.id.edit_cancel_button);
        Button editB = v.findViewById(R.id.edit_edit_button);
        // TODO: Add date button

        // TODO: add date variable

        // TODO: Set date button listener

        // TODO: Update date listener

        // set calendar change listener
        calendarView.setOnDateChangeListener((view, year, month, day) -> {
            Calendar c = Calendar.getInstance();
            c.set(year, month, day);
            long endTime = c.getTimeInMillis();
            calendarView.setDate(endTime);
        });

        // apply the task information in the input widgets
        titleView.setText(title);
        descView.setText(desc);
        switch (status) {
            case TODO:
                radioTODO.setChecked(true);
                break;
            case DOING:
                radioDOING.setChecked(true);
                break;
            case DONE:
                radioDONE.setChecked(true);
                break;
            default:
                break;
        }
        calendarView.setDate(date.getTime()); // TODO: Delete

        // set the button's listeners
        cancelB.setOnClickListener(view -> {
            // switch to list fragment
            fragmentSwitcher.switchTaskList();
        });

        editB.setOnClickListener(view -> {
            // get the new task information
            String newTitle = titleView.getText().toString();
            String newDesc = descView.getText().toString();

            int selectedRadioID = radioGroup.getCheckedRadioButtonId();
            RadioButton selectedRadio = v.findViewById(selectedRadioID);

            Date newDate = new Date(calendarView.getDate()); // TODO: Update

            // update task main information
            task.setTitle(newTitle);
            task.setDescription(newDesc);
            task.setEndDate(newDate);


            // update the task status and list
            switch (selectedRadio.getText().toString()) {
                case "To Do":
                    if (status != Status.TODO) {
                        task.setStatus(Status.TODO);
                        viewModel.addTaskTodo(task);
                    }
                    break;
                case "Doing":
                    if (status != Status.DOING) {
                        task.setStatus(Status.DOING);
                        viewModel.addTaskDoing(task);
                    }
                    break;
                case "Done":
                    if (status != Status.DONE) {
                        task.setStatus(Status.DONE);
                        viewModel.addTaskDone(task);
                    }
                    break;
                default:
                    break;
            }

            // switch to list fragment
            fragmentSwitcher.switchTaskList();
        });

        return v;
    }
}