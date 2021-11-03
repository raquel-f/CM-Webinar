package com.raquel.todoapp;

import com.raquel.todoapp.viewmodel.Task;

public interface FragmentSwitcher {
    void switchCreateTask();
    void switchEditTask(Task task);
    void switchTaskList();
}
