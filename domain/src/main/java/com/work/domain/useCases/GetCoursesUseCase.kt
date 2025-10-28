package com.work.domain.useCases

import com.work.domain.models.Course
import com.work.domain.repositories.CoursesRepository

class GetCoursesUseCase(
    private val coursesRepository: CoursesRepository
) {
    suspend operator fun invoke(): List<Course> {
        return coursesRepository.getCourses()
    }
}