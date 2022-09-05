package com.dkb.urlshortner.model

import javax.persistence.*

@Entity
@Table(name = "URL")
data class URL(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "url_id")
        var id : Long = 0 ,
        @Column(name = "original_url")
        var originalUrl : String = "",
        @Column(name = "url_hash" , unique = true, nullable = false)
        var urlKey : String = ""
        )