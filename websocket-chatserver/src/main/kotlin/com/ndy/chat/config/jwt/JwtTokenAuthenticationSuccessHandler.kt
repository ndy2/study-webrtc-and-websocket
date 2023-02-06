package com.ndy.chat.config.jwt

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import mu.KotlinLogging
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class JwtTokenAuthenticationSuccessHandler(
    private val jwtTokenEncoder: JwtTokenEncoder
) : SavedRequestAwareAuthenticationSuccessHandler() {

    private val log = KotlinLogging.logger {}

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val jwt = jwtTokenEncoder.createJwt(authentication.principal as User)
        val authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(jwt.getClaimAsString(ROLE))

        val jwtToken = JwtAuthenticationToken(jwt, authorities)
        SecurityContextHolder.getContext().authentication = jwtToken

        log.info { "authentication success! user : ${jwt.getClaimAsString(USERNAME)} , role : ${jwt.getClaimAsString(ROLE)}, jwt : ${jwt.tokenValue} " }

        super.onAuthenticationSuccess(request, response, authentication)
    }
}