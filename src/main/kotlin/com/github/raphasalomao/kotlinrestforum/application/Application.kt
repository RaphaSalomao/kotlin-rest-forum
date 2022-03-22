package com.github.raphasalomao.kotlinrestforum.application

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
@EnableEncryptableProperties
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
