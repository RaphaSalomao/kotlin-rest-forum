package com.github.raphasalomao.kotlinrestforum.application.repository

import com.github.raphasalomao.kotlinrestforum.application.model.Topic
import com.github.raphasalomao.kotlinrestforum.application.model.dto.response.CategoryCountResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TopicRepository : JpaRepository<Topic, UUID> {

    fun findByCourseName(courseName: String, pageable: Pageable): Page<Topic>

    @Query("SELECT new com.github.raphasalomao.kotlinrestforum.application.model.dto.response.CategoryCountResponse(course.category, count(t)) FROM Topic t JOIN t.course course group by course.category"   )
    fun categoryCount(): List<CategoryCountResponse>
}