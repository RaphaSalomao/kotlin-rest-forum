package com.github.raphasalomao.kotlinrestforum.application.service

import com.github.raphasalomao.kotlinrestforum.application.exceptions.NotFoundException
import com.github.raphasalomao.kotlinrestforum.application.exceptions.UnauthorizedException
import com.github.raphasalomao.kotlinrestforum.application.model.User
import com.github.raphasalomao.kotlinrestforum.application.model.UserDetail
import com.github.raphasalomao.kotlinrestforum.application.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository
): UserDetailsService {

    fun getUser(id: UUID): User {
        return userRepository.findById(id)
            .orElseThrow { NotFoundException("user.not.found") }
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findByEmail(username)
            .orElseThrow { UnauthorizedException("$username not allowed") }
        return UserDetail(user)
    }


}
