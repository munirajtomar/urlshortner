package com.dkb.urlshortner.repository

import com.dkb.urlshortner.model.URL
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UrlRepository : CrudRepository<URL,Long>{
    fun findByUrlKey(urlKey : String) : URL?
    fun save(urlObj : URL) : URL
    fun existsByOriginalUrl(url : String) : Boolean
    fun findByOriginalUrl(url : String) : URL
}