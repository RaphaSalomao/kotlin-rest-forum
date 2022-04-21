package com.github.raphasalomao.kotlinrestforum.application.factory

import com.github.raphasalomao.kotlinrestforum.application.model.Topic
import com.github.raphasalomao.kotlinrestforum.application.model.dto.response.TopicResponse
import java.util.*

object TopicFactory {

    fun buildTopic() = Topic(
        id = UUID.randomUUID(),
        title = "Test title",
        message = "Test message",
        course = CourseFactory.buildCourse(),
        author = UserFactory.buildUser()
    )

    fun buildTopicResponse() = TopicResponse(
        id = UUID.randomUUID(),
        title = "Test title",
        message = "Test message"
    )

}