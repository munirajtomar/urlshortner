package com.dkb.urlshortner.Service

import com.dkb.urlshortner.Model.URL
import com.dkb.urlshortner.Repository.UrlRepository
import com.dkb.urlshortner.Util.URLUtils
import org.springframework.stereotype.Service

@Service
class URLServiceImpl(val urlRepository : UrlRepository) : URLService {
    override fun findOriginalUrl(url: String): URL {
        return urlRepository.findByShortUrl(url);
    }

    override fun createShortURL(url: String): URL {
        val objectFound = urlRepository.findByOriginalUrl(url)
        if(objectFound!=null)
            return objectFound
        else {
            val id = URLUtils.encodeToID(url)
            val shortURL: String = "http://localhost:8080/$id"
            var urlObj = URL(shortURL, url);
            return urlRepository.save(urlObj)
        }
    }
}