package com.github.raphasalomao.kotlinrestforum.application.service

import com.github.raphasalomao.kotlinrestforum.application.exceptions.NotFoundException
import com.github.raphasalomao.kotlinrestforum.application.model.Course
import com.github.raphasalomao.kotlinrestforum.application.repository.CourseRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CourseService(
    private val courseRepository: CourseRepository
) {

    fun getCourse(id: UUID): Course {
        return courseRepository.findById(id)
            .orElseThrow { NotFoundException("course.not.found") }
    }

}
