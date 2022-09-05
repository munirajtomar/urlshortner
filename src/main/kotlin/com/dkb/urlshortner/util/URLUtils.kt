package com.dkb.urlshortner.util

import java.math.BigInteger
import java.security.MessageDigest

class URLUtils {

    companion object
    {
        fun generateHashKey(url: String): String {
            val hashBytes = MessageDigest.getInstance("MD5").digest(url.toByteArray(Charsets.UTF_8))
            val hashString = String.format("%032x", BigInteger(1, hashBytes))
            return hashString.take(6)
        }
    }
}