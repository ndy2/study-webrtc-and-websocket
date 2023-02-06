package com.ndy.chat.config.jwt

import mu.KotlinLogging
import org.springframework.security.core.userdetails.User
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm
import org.springframework.security.oauth2.jwt.*
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.temporal.ChronoUnit

@Component
class JwtTokenService(
    private val jwtEncoder: JwtEncoder,
    private val jwtDecoder: JwtDecoder,
) : JwtTokenEncoder,
    JwtTokenDecoder {

    private val log = KotlinLogging.logger {}

    override fun createJwt(user: User): Jwt {
        val headers = JwsHeader.with(SignatureAlgorithm.RS256).build()
        val claims = JwtClaimsSet.builder()
            .issuer("CHAT SERVER API")
            .subject(USERNAME)
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(5L, ChronoUnit.MINUTES))
            .claim(USERNAME, user.username)
            .claim(ROLE, user.authorities.joinToString(","))
            .build()

        return jwtEncoder.encode(JwtEncoderParameters.from(headers, claims))
    }

    override fun decode(jwt: String): Jwt {
        return jwtDecoder.decode(jwt)
    }
}