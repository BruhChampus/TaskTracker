package com.example.tasktracker.view.ui.viewmodel.viewmodels

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasktracker.data.model.TaskCard
import com.example.tasktracker.data.model.TaskCardScheduledDate
import com.example.tasktracker.data.repository.TasksRepositoryImpl
import com.example.tasktracker.view.ui.UiState.CreateScreenUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CreateScreenViewModel(private val taskTrackerRepo:TasksRepositoryImpl) : ViewModel() {

     private val _taskTitle = MutableLiveData("")
    val taskTitle: LiveData<String>
        get() = _taskTitle
    private val _taskContent = MutableLiveData("")
    val taskContent: LiveData<String>
        get() = _taskContent

    private val _timeValue = MutableLiveData("Set time")
    val timeValue: LiveData<String>
        get() = _timeValue

    private val _dateValue = MutableLiveData("Set date")
    val dateValue: LiveData<String>
        get() = _dateValue

    private val _dateInMillis = MutableLiveData(0L)
    val dateInMillis: LiveData<Long>
        get() = _dateInMillis

    private val _isWrongDate = MutableLiveData(false)
    val isWrongDate: LiveData<Boolean>
        get() = _isWrongDate


    private val _uiState = MutableStateFlow(CreateScreenUIState())
    val uiState:StateFlow<CreateScreenUIState>
        get() = _uiState.asStateFlow()



    fun updateTaskTitle(title: String) {
        _taskTitle.value = title
    }

    fun updateTaskContent(content: String) {
        _taskContent.value = content
    }

    fun updateTimeValue(time: String) {
        _timeValue.value = time
    }

    fun sendTaskCardToDB(taskCard: TaskCard){
         viewModelScope.launch(Dispatchers.IO) {
             _uiState.update { currentState -> currentState.copy(savingData = true) }
             taskTrackerRepo.insertTaskCard(taskCard)
              _uiState.update { currentState -> currentState.copy(savingData = false) }
              
         }
    }


    fun sendTaskCardScheduledDateToDB(taskCardScheduledDate: TaskCardScheduledDate){
        viewModelScope.launch(Dispatchers.IO) {
           taskTrackerRepo.insertTaskCardScheduledDate(taskCardScheduledDate)
        }
    }


    fun clearAllFields(){
        _taskTitle.value = ""
        _taskContent.value = ""
        _timeValue.value = "Set time"
        _dateValue.value = "Set date"
        _dateInMillis.value = 0L
        _isWrongDate.value = false
    }


    @OptIn(ExperimentalMaterial3Api::class)
    fun getDateTimeInMillis(
        datePickerState: DatePickerState,
        showDatePicker: MutableState<Boolean>
    ) {
        val selectedDate = Calendar.getInstance().apply {
            timeInMillis =
                datePickerState.selectedDateMillis ?: System.currentTimeMillis()
        }
        val yesterdayDate = Calendar.getInstance().apply {
            timeInMillis = (System.currentTimeMillis() - 1000 * 60 * 60 * 24)
        }
        val dateFormatter =
            SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

        //Validating date
        if (selectedDate.before(yesterdayDate)) {
            _isWrongDate.value = true
        } else {
            _dateInMillis.value = selectedDate.time.time
            _dateValue.value = dateFormatter.format(selectedDate.time)
            _isWrongDate.value = false
            showDatePicker.value = false
        }
    }
}