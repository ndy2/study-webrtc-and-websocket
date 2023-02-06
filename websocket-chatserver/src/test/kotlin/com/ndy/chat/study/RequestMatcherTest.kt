package com.ndy.chat.study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher
import org.springframework.security.web.util.matcher.OrRequestMatcher
import org.springframework.web.servlet.handler.HandlerMappingIntrospector

@SpringBootTest
class RequestMatcherTest(
    private val context: ApplicationContext
) {

    private lateinit var introspector: HandlerMappingIntrospector


    @BeforeEach
    internal fun setUp() {
        introspector = context.getBean("mvcHandlerMappingIntrospector", HandlerMappingIntrospector::class.java)
    }


    @ParameterizedTest
    @ValueSource(
        strings = [
            "webjars/sockjs-client/1.1.2/sockjs.min.js",
            "webjars/bootstrap/4.3.1/dist/css/bootstrap.min.css",
            "webjars/vue/2.5.16/dist/vue.min.js",
            "",
            "/"
        ]
    )
    internal fun permitAll(requestUri: String) {
        val permitAllRequestMatchers = listOf("webjars/**", "/")
            .map { MvcRequestMatcher(introspector, it) }
        val permitAll = OrRequestMatcher(permitAllRequestMatchers)

        val matches = permitAll.matches(MockHttpServletRequest(null, null, requestUri))

        assertThat(matches).isTrue
    }
}