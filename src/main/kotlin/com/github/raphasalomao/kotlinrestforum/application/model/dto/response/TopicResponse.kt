package com.github.raphasalomao.kotlinrestforum.application.model.dto.response

import com.github.raphasalomao.kotlinrestforum.application.model.enum.TopicStatus
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

data class TopicResponse (
    val id: UUID = UUID.randomUUID(),
    var title: String,
    var message: String,
    var status: TopicStatus = TopicStatus.OPEN,
    val createdAt: LocalDateTime = LocalDateTime.now(),
): Serializable