package com.ndy.chat.config.jwt

import org.springframework.security.core.userdetails.User
import org.springframework.security.oauth2.jwt.Jwt

interface JwtTokenEncoder {

    fun createJwt(user: User): Jwt
}