package com.work.domain.useCases

import com.work.domain.models.Course

class SortCoursesUseCase {
    operator fun invoke(
        courses: List<Course>,
        ascending: Boolean
    ): List<Course> {
        return if (ascending) {
            courses.sortedBy { it.publishDate }
        } else {
            courses.sortedByDescending { it.publishDate }
        }
    }
}