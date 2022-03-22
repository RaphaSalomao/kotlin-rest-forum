package com.github.raphasalomao.kotlinrestforum.application.exceptions

import com.github.raphasalomao.kotlinrestforum.application.model.dto.response.ErrorResponse
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ExceptionHandler {

    private val log = KotlinLogging.logger { }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(
        exception: NotFoundException,
        request: HttpServletRequest
    ): ErrorResponse {
        return ErrorResponse(
            error = HttpStatus.NOT_FOUND.name,
            message = exception.message ?: "none",
            path = request.servletPath
        )
    }

    @ExceptionHandler(
        value = [MethodArgumentNotValidException::class, MethodArgumentTypeMismatchException::class]
    )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBadRequest(
        request: HttpServletRequest
    ): ErrorResponse {
        return ErrorResponse(
            error = HttpStatus.BAD_REQUEST.name,
            message = "Validation failed for request",
            path = request.servletPath
        )
    }

    @ExceptionHandler(
        value = [UnauthorizedException::class]
    )
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleUnauthorized(
        exception: UnauthorizedException,
        request: HttpServletRequest
    ): ErrorResponse {
        return ErrorResponse(
            error = HttpStatus.UNAUTHORIZED.name,
            message = exception.message ?: "User not allowed",
            path = request.servletPath
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleInternalError(
        exception: Exception,
        request: HttpServletRequest
    ): ErrorResponse {
        exception.printStackTrace()
        log.error {
            exception.message
        }
        return ErrorResponse(
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = "Unhandled server error",
            path = request.servletPath
        )
    }
}