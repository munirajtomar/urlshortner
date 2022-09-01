package com.dkb.urlshortner.Model

import java.lang.reflect.Constructor
import javax.persistence.*

@Entity
@Table(name = "URL")
data class URL(
        @Column(name = "short_url")
        var shortUrl : String? = null,
        @Column(name = "original_url")
        var originalUrl : String? = null ,
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "url_id")
        var id : Long? = null ,
        )