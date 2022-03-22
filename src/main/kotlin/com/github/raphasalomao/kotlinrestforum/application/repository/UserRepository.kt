package com.github.raphasalomao.kotlinrestforum.application.repository

import com.github.raphasalomao.kotlinrestforum.application.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: JpaRepository<User, UUID> {
    fun findByEmail(username: String?): Optional<User>
}

