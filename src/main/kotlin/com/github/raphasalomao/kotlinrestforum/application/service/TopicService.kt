package com.github.raphasalomao.kotlinrestforum.application.service

import com.github.raphasalomao.kotlinrestforum.application.exceptions.NotFoundException
import com.github.raphasalomao.kotlinrestforum.application.mapper.TopicCreationRequestToTopic
import com.github.raphasalomao.kotlinrestforum.application.mapper.TopicToTopicResponseMapper
import com.github.raphasalomao.kotlinrestforum.application.model.dto.request.TopicCreationRequest
import com.github.raphasalomao.kotlinrestforum.application.model.dto.request.TopicUpdateRequest
import com.github.raphasalomao.kotlinrestforum.application.model.dto.response.CategoryCountResponse
import com.github.raphasalomao.kotlinrestforum.application.model.dto.response.TopicResponse
import com.github.raphasalomao.kotlinrestforum.application.repository.TopicRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class TopicService(
    private val topicRepository: TopicRepository,
    private val topicToTopicResponseMapper: TopicToTopicResponseMapper,
    private val topicCreationRequestToTopic: TopicCreationRequestToTopic
) {

    @Cacheable("firstCache")
    fun getTopics(courseName: String?, pageable: Pageable): Page<TopicResponse> {
        println("Calling get topics")
        return if (courseName != null) {
            topicRepository.findByCourseName(courseName, pageable)
                .map { topicToTopicResponseMapper.map(it) }
        } else {
            topicRepository.findAll(pageable)
                .map { topicToTopicResponseMapper.map(it) }
        }
    }

    fun getTopic(id: UUID): TopicResponse {
        return topicToTopicResponseMapper.map(
            topicRepository.findById(id).orElseThrow { NotFoundException("topic.not.found") }
        )
    }

    @CacheEvict(value = ["firstCache"], allEntries = true)
    fun createTopic(request: TopicCreationRequest): UUID {
        return topicRepository.save(topicCreationRequestToTopic.map(request)).id
    }

    @CacheEvict(value = ["firstCache"], allEntries = true)
    fun updateTopic(request: TopicUpdateRequest) {
        topicRepository.findById(request.id).ifPresentOrElse({
            it.apply {
                message = request.message
                title = request.title
            }.let { topicRepository.save(it) }
        }, { NotFoundException("topic.not.found") })
    }

    @CacheEvict(value = ["firstCache"], allEntries = true)
    fun delete(id: UUID) {
        topicRepository.deleteById(id)
    }

    fun categoryCount(): List<CategoryCountResponse> {
        return topicRepository.categoryCount()
    }
}

