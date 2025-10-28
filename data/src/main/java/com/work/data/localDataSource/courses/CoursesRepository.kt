package com.work.data.localDataSource.courses

import com.work.data.localDataSource.models.Course

interface CoursesRepository {
    suspend fun getCourses(): List<Course>
}