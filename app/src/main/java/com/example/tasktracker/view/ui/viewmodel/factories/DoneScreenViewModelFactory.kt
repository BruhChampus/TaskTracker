package com.example.tasktracker.view.ui.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tasktracker.data.repository.TasksRepositoryImpl
import com.example.tasktracker.view.ui.viewmodel.DoneScreenViewModel
import com.example.tasktracker.view.ui.viewmodel.HomeScreenViewModel

class DoneScreenViewModelFactory(private val repository: TasksRepositoryImpl) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DoneScreenViewModel(repository) as T
    }
}