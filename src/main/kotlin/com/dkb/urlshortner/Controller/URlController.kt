package com.dkb.urlshortner.Controller

import ch.qos.logback.core.net.SyslogOutputStream
import com.dkb.urlshortner.Model.URL
import com.dkb.urlshortner.Service.URLService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URLEncoder
import javax.servlet.http.HttpServletRequest

@RestController()
@RequestMapping("/api/v1/url")
class URlController(val urlService: URLService) {

    @GetMapping("/**")
    fun getLongUrl(request : HttpServletRequest) : ResponseEntity<URL> {
        val fullUrl = request.requestURL.toString()
        val shortUrl = fullUrl.split("/url/").toTypedArray()[1]
        val urlObj = urlService.findOriginalUrl(shortUrl)
        if (urlObj != null) {
            return ResponseEntity<URL>(urlObj,HttpStatus.OK)
        }
        else {
            return ResponseEntity<URL>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    fun createShortUrl(@RequestParam(value = "url") url : String) : ResponseEntity<URL> {
        val urlObj = urlService.createShortURL(url);
        if (urlObj != null) {
            return ResponseEntity<URL>(urlObj,HttpStatus.CREATED)
        }
        else {
            return ResponseEntity<URL>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}