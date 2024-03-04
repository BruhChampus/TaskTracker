package com.example.tasktracker.view.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tasktracker.data.repository.TasksRepositoryImpl

class CreateScreenViewModelFactory(private val repository:TasksRepositoryImpl):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateScreenViewModel(repository) as T
    }

}