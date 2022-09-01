package com.dkb.urlshortner.Service

import com.dkb.urlshortner.Model.URL

interface URLService {
    fun findOriginalUrl(url: String) : URL
    fun createShortURL(url : String) : URL
}