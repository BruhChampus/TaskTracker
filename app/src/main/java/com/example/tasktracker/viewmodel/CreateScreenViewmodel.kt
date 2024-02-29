package com.example.tasktracker.viewmodel

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CreateScreenViewmodel : ViewModel() {

    //textTitleState:String, taskContentState:String, timeState:String, dateStateInMillis:Long
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


    fun updateTaskTitle(title: String) {
        _taskTitle.value = title
    }

    fun updateTaskContent(content: String) {
        _taskContent.value = content
    }

    fun updateTimeValue(time: String) {
        _timeValue.value = time
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