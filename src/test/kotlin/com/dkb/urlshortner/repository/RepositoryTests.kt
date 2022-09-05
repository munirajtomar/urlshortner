package com.dkb.urlshortner.repository

import com.dkb.urlshortner.UrlShortnerApplication
import com.dkb.urlshortner.model.URL
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [UrlShortnerApplication::class])
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class RepositoryTests {

    @Autowired
    private lateinit var urlRepository: UrlRepository

    @Test
    fun testSave() {
        val savedUrl = urlRepository.save(URL(originalUrl = "http://abcd.example.com", urlKey = "abcdef"))
        val urlFromDb = urlRepository.findByIdOrNull(savedUrl.id)
        assertThat(savedUrl).isEqualTo(urlFromDb)
    }

    @Test(expected = DataIntegrityViolationException::class)
    fun testSaveWithSameUrlKey() {
        urlRepository.save(URL(originalUrl = "http://abcd.example.com", urlKey = "123456"))
        urlRepository.save(URL(originalUrl = "http://abcd.example.com", urlKey = "123456"))
    }

    @Test
    fun testIfOriginalUrlExists() {
        val url = "http://abcd.example.com"
        urlRepository.save(URL(originalUrl = url, urlKey = ""))
        assertThat(urlRepository.existsByOriginalUrl(url)).isTrue()
    }
}