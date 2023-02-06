package com.ndy.chat.config.jwt

import org.springframework.security.oauth2.jwt.Jwt

interface JwtTokenDecoder {

    fun decode(jwt: String): Jwt

    fun validate(jwt: String) {
        decode(jwt)
    }
}