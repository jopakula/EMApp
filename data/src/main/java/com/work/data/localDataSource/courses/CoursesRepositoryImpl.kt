package com.work.data.localDataSource.courses

import com.work.data.localDataSource.models.Course

class CoursesRepositoryImpl() : CoursesRepository {
    override suspend fun getCourses(): List<Course> = MockData.getCourses()
}