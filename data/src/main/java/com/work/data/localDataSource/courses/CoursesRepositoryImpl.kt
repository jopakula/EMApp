package com.work.data.localDataSource.courses

import com.work.data.mapper.toDomain
import com.work.domain.models.Course
import com.work.domain.repositories.CoursesRepository

class CoursesRepositoryImpl() : CoursesRepository {

    override suspend fun getCourses(): List<Course> {
        val data = MockData.getCourses()
        return data.toDomain()
    }

}