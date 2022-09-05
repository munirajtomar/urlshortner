package com.dkb.urlshortner.controller

import com.dkb.urlshortner.model.URL
import com.dkb.urlshortner.model.URLDto
import com.dkb.urlshortner.service.URLService
import com.dkb.urlshortner.util.URLUtils
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("/api/v1/url")
class URlController(val urlService: URLService) {

    @ApiOperation(value = "Get Original Url", notes = "Finds original url from url key")
    @GetMapping("/{urlKey}")
    fun getLongUrl(@PathVariable urlKey : String) : ResponseEntity<Any> {
        val urlObj = urlService.findOriginalUrl(urlKey)
        if (urlObj == null) {
            return ResponseEntity("Requested url key not found.",HttpStatus.NOT_FOUND)
        }
        else {
            return ResponseEntity(urlObj,HttpStatus.FOUND);
        }
    }

    @ApiOperation(value = "Create new short url", notes = "Converts long url to short url")
    @PostMapping
    fun createShortUrl(@RequestBody urlRequest : URLDto) : ResponseEntity<Any> {
        if(urlRequest.originalUrl.isNullOrEmpty())
        {
            return ResponseEntity("Requested URL is invalid.",HttpStatus.BAD_REQUEST)
        }
        else if(urlService.existsOriginalUrl(urlRequest.originalUrl))
        {
            val savedUrl = urlService.findUrl(urlRequest.originalUrl)
            return ResponseEntity(convertToDto(savedUrl.originalUrl,savedUrl.urlKey), HttpStatus.OK)
        }
        else
        {
            val urlObj = urlService.saveUrl(URL(originalUrl = urlRequest.originalUrl, urlKey = URLUtils.generateHashKey(urlRequest.originalUrl)))
            return ResponseEntity(convertToDto(urlObj.originalUrl, urlObj.urlKey), HttpStatus.CREATED)
        }
    }

    private fun convertToDto(originalUrl: String, urlKey: String): URLDto {
        return URLDto(originalUrl, urlKey)
    }
}