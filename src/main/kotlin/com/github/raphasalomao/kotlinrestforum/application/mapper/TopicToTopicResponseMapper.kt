package com.github.raphasalomao.kotlinrestforum.application.mapper

import com.github.raphasalomao.kotlinrestforum.application.model.Topic
import com.github.raphasalomao.kotlinrestforum.application.model.dto.response.TopicResponse
import org.springframework.stereotype.Component

@Component
class TopicToTopicResponseMapper: Mapper<Topic, TopicResponse> {

    override fun map(t: Topic): TopicResponse {
        return TopicResponse(
            t.id,
            t.title,
            t.message,
            t.status,
            t.createdAt,
            t.updatedAt
        )
    }
}