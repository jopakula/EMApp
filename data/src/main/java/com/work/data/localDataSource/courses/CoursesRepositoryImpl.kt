package com.work.data.localDataSource.courses

import com.work.domain.models.Course
import com.work.domain.repositories.CoursesRepository

class CoursesRepositoryImpl() : CoursesRepository {
    override suspend fun getCourses(): List<Course> = MockData.getCourses()
}