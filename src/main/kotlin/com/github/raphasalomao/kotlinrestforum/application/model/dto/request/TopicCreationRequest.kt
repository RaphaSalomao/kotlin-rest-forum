package com.github.raphasalomao.kotlinrestforum.application.model.dto.request

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class TopicCreationRequest(
    @field:NotEmpty
    val title: String,

    @field:NotEmpty
    val message: String,

    @field:NotNull
    @field:Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
    val courseId: String,

    @field:NotNull
    @field:Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
    val authorId: String,
) {
}