package com.github.raphasalomao.kotlinrestforum.application.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.raphasalomao.kotlinrestforum.application.configuration.JwtUtils
import com.github.raphasalomao.kotlinrestforum.application.model.Credentials
import mu.KotlinLogging
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTLoginFilter(
    private val authManager: AuthenticationManager,
    private val jwUtil: JwtUtils
) : UsernamePasswordAuthenticationFilter() {

    private val log = KotlinLogging.logger { }

    init {
        setFilterProcessesUrl("/v1/login")
    }

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val (username, password) = ObjectMapper().readValue(request?.inputStream, Credentials::class.java)
        val token = UsernamePasswordAuthenticationToken(username, password)
        return authManager.authenticate(token)
    }


    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val user = (authResult?.principal as UserDetails)
        val token = jwUtil.generateToken(user.username, user.authorities)
        response?.addHeader("Authorization", "Bearer $token")
    }

    override fun setFilterProcessesUrl(filterProcessesUrl: String?) {
        super.setFilterProcessesUrl(filterProcessesUrl)
    }
}
