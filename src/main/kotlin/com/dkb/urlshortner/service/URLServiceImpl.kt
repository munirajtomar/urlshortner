package com.dkb.urlshortner.service

import com.dkb.urlshortner.model.URL
import com.dkb.urlshortner.repository.UrlRepository
import org.springframework.stereotype.Service

@Service
class URLServiceImpl(val urlRepository : UrlRepository) : URLService {
    override fun findOriginalUrl(urlKey: String): URL? {
        return urlRepository.findByUrlKey(urlKey)
    }

    override fun saveUrl(urlObj: URL): URL {
        return urlRepository.save(urlObj)
    }

    override fun existsOriginalUrl(url: String): Boolean {
        return urlRepository.existsByOriginalUrl(url)
    }

    override fun findUrl(url: String): URL {
        return urlRepository.findByOriginalUrl(url)
    }
}