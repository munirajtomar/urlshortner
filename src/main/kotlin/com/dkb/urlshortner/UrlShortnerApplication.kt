package com.dkb.urlshortner

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@EnableAutoConfiguration
@ComponentScan
class UrlShortnerApplication

fun main(args: Array<String>) {
	runApplication<UrlShortnerApplication>(*args)
}
