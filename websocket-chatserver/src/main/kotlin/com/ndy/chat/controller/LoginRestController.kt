package com.ndy.chat.controller

import com.ndy.chat.config.jwt.username
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/chat")
@RestController
class LoginRestController {

    @GetMapping("/user")
    fun getUserInfo(@AuthenticationPrincipal jwt: Jwt): Map<String, String> {

        return mapOf(
            "name" to jwt.username(),
            "token" to jwt.tokenValue
        )
    }
}