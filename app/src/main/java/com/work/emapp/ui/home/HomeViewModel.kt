package com.work.emapp.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.work.data.Course
import com.work.data.MockData

class HomeViewModel : ViewModel() {
    private val _courses = mutableStateOf<List<Course>>(emptyList())
    val courses: State<List<Course>> = _courses

    private val _selectedCourse = mutableStateOf<Course?>(null)
    val selectedCourse: State<Course?> = _selectedCourse

    init {
        _courses.value = MockData.getCourses()
    }

    fun chooseCourse(course: Course) {
        _selectedCourse.value = course
    }
}