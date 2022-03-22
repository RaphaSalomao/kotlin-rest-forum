package com.github.raphasalomao.kotlinrestforum.application.mapper

import com.github.raphasalomao.kotlinrestforum.application.model.Topic
import com.github.raphasalomao.kotlinrestforum.application.model.dto.request.TopicCreationRequest
import com.github.raphasalomao.kotlinrestforum.application.service.CourseService
import com.github.raphasalomao.kotlinrestforum.application.service.UserService
import org.springframework.stereotype.Component
import java.util.*

@Component
class TopicCreationRequestToTopic(
    private val userService: UserService,
    private val courseService: CourseService
) : Mapper<TopicCreationRequest, Topic> {
    override fun map(t: TopicCreationRequest): Topic {
        return Topic(
            title = t.title,
            message = t.message,
            author = userService.getUser(UUID.fromString(t.authorId)),
            course = courseService.getCourse(UUID.fromString(t.courseId))
        )
    }
}