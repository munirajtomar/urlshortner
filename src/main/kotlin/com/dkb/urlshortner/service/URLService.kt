package com.dkb.urlshortner.service

import com.dkb.urlshortner.model.URL

interface URLService {
    fun findOriginalUrl(urlKey: String) : URL?
    fun saveUrl(urlObj : URL) : URL
    fun existsOriginalUrl(url : String) : Boolean
    fun findUrl(url: String) : URL
}