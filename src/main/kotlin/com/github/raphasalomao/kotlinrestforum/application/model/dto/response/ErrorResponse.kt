package com.github.raphasalomao.kotlinrestforum.application.model.dto.response

data class ErrorResponse(
    val error: String,
    val message: String,
    val path: String?
)