package com.ndy.chat.config.jwt

import org.springframework.security.oauth2.jwt.Jwt

fun Jwt.username() = getClaimAsString("username")

