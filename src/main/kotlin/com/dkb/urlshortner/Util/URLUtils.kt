package com.dkb.urlshortner.Util

import java.math.BigInteger
import java.security.MessageDigest

class URLUtils {
    companion object{
        fun encodeToID(url : String): String {
            val hashBytes = MessageDigest.getInstance("MD5").digest(url.toByteArray(Charsets.UTF_8))
            val hashString = String.format("%032x", BigInteger(1, hashBytes))
            val truncatedHashString = hashString.take(6)
            return truncatedHashString
        }
    }
}