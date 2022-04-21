package com.github.raphasalomao.kotlinrestforum.application.service

import com.github.raphasalomao.kotlinrestforum.application.exceptions.NotFoundException
import com.github.raphasalomao.kotlinrestforum.application.factory.TopicFactory
import com.github.raphasalomao.kotlinrestforum.application.mapper.TopicCreationRequestToTopic
import com.github.raphasalomao.kotlinrestforum.application.mapper.TopicToTopicResponseMapper
import com.github.raphasalomao.kotlinrestforum.application.model.Topic
import com.github.raphasalomao.kotlinrestforum.application.repository.TopicRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*

class TopicServiceTest {
    private val topics = listOf(TopicFactory.buildTopic())
    private val pageable: Pageable = mockk()

    private val topicRepository: TopicRepository = mockk {
        every { findAll(pageable) } returns PageImpl(topics)
        every { findByCourseName(any(), any()) } returns PageImpl(topics)
    }

    private val slot = slot<Topic>()
    private val topicToTopicResponseMapper: TopicToTopicResponseMapper = mockk {
        every { map(capture(slot)) } returns TopicFactory.buildTopicResponse()
    }
    private val topicCreationRequestToTopic: TopicCreationRequestToTopic = mockk()

    private val topicService = TopicService(
        topicRepository,
        topicToTopicResponseMapper,
        topicCreationRequestToTopic
    )

    @Test
    fun `should return topics calling topicRepository_findByCourseName`() {
        topicService.getTopics("course", pageable)

        assertThat(slot.captured).isEqualTo(topics.first())
        verify(exactly = 1) { topicRepository.findByCourseName(any(), any()) }
        verify(exactly = 1) { topicToTopicResponseMapper.map(any()) }
        verify(exactly = 0) { topicRepository.findAll(pageable) }
    }

    @Test
    fun `should return topics calling topicRepository_findAll`() {
        topicService.getTopics(null, pageable)

        assertThat(slot.captured).isEqualTo(topics.first())
        verify(exactly = 1) { topicToTopicResponseMapper.map(any()) }
        verify(exactly = 1) { topicRepository.findAll(pageable) }
        verify(exactly = 0) { topicRepository.findByCourseName(any(), any()) }
    }

    @Test
    fun `should throw NotFoundException`() {
        every { topicRepository.findById(any()) } returns Optional.empty()

        val actual = assertThrows<NotFoundException> { topicService.getTopic(UUID.randomUUID()) }

        assertThat(actual::class).isEqualTo(NotFoundException::class)
    }
}