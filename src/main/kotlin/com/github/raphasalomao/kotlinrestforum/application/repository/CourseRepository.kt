package com.github.raphasalomao.kotlinrestforum.application.repository

import com.github.raphasalomao.kotlinrestforum.application.model.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CourseRepository: JpaRepository<Course, UUID> {
}