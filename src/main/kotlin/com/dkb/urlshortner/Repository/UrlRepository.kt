package com.dkb.urlshortner.Repository

import com.dkb.urlshortner.Model.URL
import org.springframework.data.repository.CrudRepository
import org.springframework.lang.Nullable
import org.springframework.stereotype.Repository

@Repository
interface UrlRepository : CrudRepository<URL,Long>{
    @Nullable
    fun findByShortUrl(url : String) : URL
    @Nullable
    fun findByOriginalUrl(url : String) : URL
    fun save(url : String) : URL
}