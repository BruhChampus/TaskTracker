package com.example.tasktracker.view.ui.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tasktracker.data.repository.TasksRepositoryImpl
import com.example.tasktracker.view.ui.viewmodel.CreateScreenViewModel
import com.example.tasktracker.view.ui.viewmodel.HomeScreenViewModel

class HomeScreenViewModelFactory(private val repository: TasksRepositoryImpl) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeScreenViewModel(repository) as T
    }
}