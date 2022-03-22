package com.github.raphasalomao.kotlinrestforum.application.model

import com.github.raphasalomao.kotlinrestforum.application.model.enum.TopicStatus
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
data class Topic(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),
    var title: String,
    var message: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    var course: Course,

    @ManyToOne
    var author: User,

    @Enumerated(EnumType.STRING)
    var status: TopicStatus = TopicStatus.OPEN,

    @OneToMany(mappedBy = "topic")
    var asnwers: List<Answer> = emptyList()
)