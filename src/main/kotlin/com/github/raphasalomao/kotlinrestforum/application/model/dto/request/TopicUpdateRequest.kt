package com.github.raphasalomao.kotlinrestforum.application.model.dto.request

import java.util.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class TopicUpdateRequest(
    @field:NotNull
    val id: UUID,

    @field:NotEmpty
    val title: String,

    @field:NotEmpty
    val message: String
)