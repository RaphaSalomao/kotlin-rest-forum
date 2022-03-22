package com.github.raphasalomao.kotlinrestforum.application.model

import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Answer(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),
    val message: String,
    val cretedAt: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    val author: User,
    @ManyToOne
    val topic: Topic,
    val solution: Boolean = false
)
