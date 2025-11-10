package com.work.data.mapper

import com.work.data.localDataSource.models.CourseDto
import com.work.domain.models.Course

fun CourseDto.toDomain(): Course = Course(
    id = id,
    title = title,
    text = text,
    price = price,
    rate = rate,
    startDate = startDate,
    hasLike = hasLike,
    publishDate = publishDate
)

fun List<CourseDto>.toDomain(): List<Course> = map { it.toDomain() }