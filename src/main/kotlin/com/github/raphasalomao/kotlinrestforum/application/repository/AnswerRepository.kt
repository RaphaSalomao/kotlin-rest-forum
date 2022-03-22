package com.github.raphasalomao.kotlinrestforum.application.repository

import com.github.raphasalomao.kotlinrestforum.application.model.Answer
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AnswerRepository: JpaRepository<Answer, UUID> {
}