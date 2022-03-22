package com.github.raphasalomao.kotlinrestforum.application.model

import org.springframework.security.core.GrantedAuthority
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.AUTO
import javax.persistence.Id

@Entity
data class Role(
    @Id
    @GeneratedValue(strategy = AUTO)
    val id: UUID,
    val name: String
): GrantedAuthority {
    override fun getAuthority(): String = name
}