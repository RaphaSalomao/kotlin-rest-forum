package com.github.raphasalomao.kotlinrestforum.application.factory

import com.github.raphasalomao.kotlinrestforum.application.model.Role
import com.github.raphasalomao.kotlinrestforum.application.model.User
import java.util.*

object UserFactory {
    fun buildUser() = User(
        id = UUID.randomUUID(),
        name = "Test User",
        email = "test@test.com",
        password = "password",
        roles = listOf(
            Role(
                UUID.randomUUID(),
                "TEST_ROLE"
            )
        )
    )
}
