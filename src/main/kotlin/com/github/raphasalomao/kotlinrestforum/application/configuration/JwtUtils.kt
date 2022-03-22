package com.github.raphasalomao.kotlinrestforum.application.configuration

import com.github.raphasalomao.kotlinrestforum.application.service.UserService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm.HS512
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtils(
    private val userService: UserService
) {

    private val expiration: Long = 3600000

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    private val log = KotlinLogging.logger { }

    fun generateToken(username: String, authorities: MutableCollection<out GrantedAuthority>): String? {
        val roles: HashSet<GrantedAuthority> = authorities.toHashSet()
        return Jwts.builder()
            .setSubject(username)
            .claim("role", roles)
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(HS512, secret.toByteArray())
            .compact()
    }

    fun isValid(jwt: String?): Boolean {
        return try {
            jwsClaims(jwt)
            true
        } catch (e: java.lang.IllegalArgumentException) {
            false
        }
    }

    fun getAuthentication(jwt: String?): Authentication {
        val username = jwsClaims(jwt).body.subject
        val authorities = userService.loadUserByUsername(username).authorities
        return UsernamePasswordAuthenticationToken(username, null, authorities)

    }

    fun jwsClaims(jwt: String?): Jws<Claims> {
        return Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(jwt)
    }
}