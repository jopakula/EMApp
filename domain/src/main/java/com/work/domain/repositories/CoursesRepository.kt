package com.work.domain.repositories

import com.work.domain.models.Course

interface CoursesRepository {
    suspend fun getCourses(): List<Course>
}