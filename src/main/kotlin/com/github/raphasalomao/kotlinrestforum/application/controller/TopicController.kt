package com.github.raphasalomao.kotlinrestforum.application.controller

import com.github.raphasalomao.kotlinrestforum.application.model.dto.request.TopicCreationRequest
import com.github.raphasalomao.kotlinrestforum.application.model.dto.request.TopicUpdateRequest
import com.github.raphasalomao.kotlinrestforum.application.model.dto.response.CategoryCountResponse
import com.github.raphasalomao.kotlinrestforum.application.model.dto.response.TopicResponse
import com.github.raphasalomao.kotlinrestforum.application.service.TopicService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/topics")
class TopicController(private val service: TopicService) {

    @GetMapping
    fun getTopics(
        @RequestParam(required = false) courseName: String?,
        @PageableDefault pageable: Pageable
    ): ResponseEntity<Page<TopicResponse>> {
        return ResponseEntity.ok(service.
        getTopics(courseName, pageable))
    }

    @GetMapping("/{id}")
    fun getTopic(@PathVariable id: UUID): ResponseEntity<TopicResponse> {
        return ResponseEntity.ok(service.getTopic(id))
    }

    @PostMapping
    fun createTopic(
        @RequestBody @Valid request: TopicCreationRequest,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<Void> {
        return ResponseEntity
            .created(
                uriBuilder
                    .path("/v1/topics/${service.createTopic(request)}")
                    .build()
                    .toUri())
            .build()
    }

    @PutMapping
    fun updateTopic(@RequestBody @Valid request: TopicUpdateRequest): ResponseEntity<Void> {
        service.updateTopic(request)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{id}")
    fun deleteTopic(@PathVariable id: UUID): ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/category-count")
    fun categoryCount(): ResponseEntity<List<CategoryCountResponse>> {
        return ResponseEntity.ok(service.categoryCount())
    }
}