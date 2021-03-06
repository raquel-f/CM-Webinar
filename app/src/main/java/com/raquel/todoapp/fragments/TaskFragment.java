package com.raquel.todoapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.raquel.todoapp.FragmentSwitcher;
import com.raquel.todoapp.MainActivity;
import com.raquel.todoapp.R;
import com.raquel.todoapp.viewmodel.Task;
import com.raquel.todoapp.viewmodel.TaskViewModel;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class TaskFragment extends Fragment {

    private TaskViewModel viewModel;

    private FragmentSwitcher fragmentSwitcher;

    private static final String ARG_COLUMN_COUNT = "column-count";
    private MyTaskRecyclerViewAdapter adapter;

    @SuppressWarnings("unused")
    public static TaskFragment newInstance(int columnCount) {
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TaskFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize the view model variable
        viewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);

        setHasOptionsMenu(true);

        // initialize the interface
        if (getActivity() instanceof MainActivity) {
            fragmentSwitcher = (FragmentSwitcher) getActivity();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        // Get context
        Context context = view.getContext();

        //TODO add drawer button

        //get recyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        adapter = new MyTaskRecyclerViewAdapter(viewModel.getTodoTasks(), fragmentSwitcher, viewModel);

        recyclerView.setAdapter(adapter);

        // set button onClick event listener
        ImageButton button = view.findViewById(R.id.addTaskButton); // TODO change this
        button.setOnClickListener(view1 -> fragmentSwitcher.switchCreateTask());

        return view;
    }

    // Menu stuff TODO remove this part
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.list_menu, menu);
    }


    // TODO change this
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        List<Task> newList;

        if (item.getItemId() == R.id.todo) {
            newList = viewModel.getTodoTasks();
        } else if (item.getItemId() == R.id.doing) {
            newList = viewModel.getDoingTasks();
        } else if (item.getItemId() == R.id.done) {
            newList = viewModel.getDoneTasks();
        } else {
            return super.onOptionsItemSelected(item);
        }

        adapter.setDataSet(newList);
        adapter.notifyDataSetChanged();

        return true;
    }
}