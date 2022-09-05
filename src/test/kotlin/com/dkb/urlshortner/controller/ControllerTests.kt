package com.dkb.urlshortner.controller

import com.dkb.urlshortner.UrlShortnerApplication
import com.dkb.urlshortner.model.URL
import com.dkb.urlshortner.model.URLDto
import com.dkb.urlshortner.repository.UrlRepository
import com.dkb.urlshortner.service.URLService
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [UrlShortnerApplication::class])
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureMockMvc
class ControllerTests {
    @Autowired
    private lateinit var urlRepository: UrlRepository

    @Autowired
    private lateinit var urlService: URLService

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun testUrlWithWrongHashKey() {
        mockMvc.perform(get("/api/v1/url/sdfghjkl")).andExpect(status().isNotFound)
    }

    @Test
    fun testUrlWithCorrectHashKey() {
        val originalUrl = "http://abc@example.com"
        val urlKey = "abcdef"
        urlService.saveUrl(URL(originalUrl = originalUrl, urlKey = urlKey))
        mockMvc.perform(get("/api/v1/url/$urlKey"))
                .andExpect(status().isFound)
    }

    @Test
    fun sendBadRequest() {
        performPost("", "").andExpect(status().isBadRequest)
        performPost("", "asd").andExpect(status().isBadRequest)
    }

    @Test
    fun createUrl() {
        val originalUrl = "http://onet.pl"
        val result = performPost(originalUrl, "").andExpect(status().isCreated).andReturn()
        val url = objectMapper.readValue(result.response.contentAsString, URLDto::class.java)
        val urlFromDb = urlRepository.findByUrlKey(url.urlKey)
        assertThat(urlFromDb).isNotNull()
        assertThat(originalUrl).isEqualTo(urlFromDb?.originalUrl)
    }

    private fun performPost(originalUrl: String, urlKey: String): ResultActions {
        return mockMvc.perform(post("/api/v1/url/")
                .content(objectMapper.writeValueAsString(URLDto(originalUrl = originalUrl, urlKey = urlKey)))
                .contentType("application/json"))
    }

}