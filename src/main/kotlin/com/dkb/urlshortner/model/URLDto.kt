package com.dkb.urlshortner.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(description = "Request object for POST method")
data class URLDto(
        @ApiModelProperty(required = true, notes = "Original Url to convert to short url key", value = "originalUrl", example = "abcd@example.com")
        val originalUrl: String = "",
        @ApiModelProperty(required = false, notes = "Short Url Key", hidden = true)
        val urlKey: String = ""
)