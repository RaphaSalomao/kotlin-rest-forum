package com.github.raphasalomao.kotlinrestforum.application.factory

import com.github.raphasalomao.kotlinrestforum.application.model.Course
import java.util.*

object CourseFactory {
    fun buildCourse() = Course(
        id = UUID.randomUUID(),
        name = "Test Course",
        category = "Test Category",
    )
}
