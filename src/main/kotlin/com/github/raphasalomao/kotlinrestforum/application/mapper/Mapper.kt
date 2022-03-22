package com.github.raphasalomao.kotlinrestforum.application.mapper

interface Mapper<T, U> {

    fun map(t: T): U

}
