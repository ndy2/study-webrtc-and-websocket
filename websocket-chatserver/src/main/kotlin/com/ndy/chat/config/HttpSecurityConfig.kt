package com.ndy.chat.config

import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authorization.AuthorityAuthorizationManager
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.intercept.RequestAuthorizationContext
import org.springframework.security.web.access.intercept.RequestMatcherDelegatingAuthorizationManager
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher
import org.springframework.security.web.util.matcher.OrRequestMatcher
import org.springframework.web.servlet.handler.HandlerMappingIntrospector


@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
class HttpSecurityConfig {

    @Bean
    fun filterChain(
        http: HttpSecurity,
        access: AuthorizationManager<RequestAuthorizationContext>,
        successHandler: AuthenticationSuccessHandler
    ): SecurityFilterChain {
        return http
            .csrf().disable()
            .httpBasic().disable()
            .headers().frameOptions().sameOrigin()
            .and()
            .formLogin {
                it.defaultSuccessUrl("/chat/room")
                    .successHandler(successHandler)
            }
            .authorizeHttpRequests { it.anyRequest().access(access) }
            .build()
    }

    @Bean
    fun requestMatcherAuthorizationManager(introspector: HandlerMappingIntrospector): AuthorizationManager<RequestAuthorizationContext> {
        fun toAllRequestMatcher(vararg list: String) =
            OrRequestMatcher(list.map { MvcRequestMatcher(introspector, it) })

        // permit all
        val permitAllPath = toAllRequestMatcher("/webjars/**", "/error/**", "/", "/favicon.ico")
        val permitAll = AuthorizationManager<RequestAuthorizationContext> { _, _ -> AuthorizationDecision(true) }

        // role user
        val roleUserPath = toAllRequestMatcher("/chat/**", "/ws-stomp/**")
        val roleUser = AuthorityAuthorizationManager.hasRole<RequestAuthorizationContext>("USER")

        // build RequestMatcherDelegatingAuthorizationManager
        val manager = RequestMatcherDelegatingAuthorizationManager.builder()
            .add(permitAllPath, permitAll)
            .add(roleUserPath, roleUser)
            .build()

        //warp manager and return
        return AuthorizationManager { a, context -> manager.check(a, context.request) }
    }

    @Bean
    fun userDetailsService(): InMemoryUserDetailsManager? {
        val user1: UserDetails = User.withDefaultPasswordEncoder()
            .username("user1")
            .password("user1")
            .roles("USER")
            .build()

        val user2: UserDetails = User.withDefaultPasswordEncoder()
            .username("user2")
            .password("user2")
            .roles("USER")
            .build()

        val guest1: UserDetails = User.withDefaultPasswordEncoder()
            .username("guest1")
            .password("guest1")
            .roles("GUEST")
            .build()

        return InMemoryUserDetailsManager(user1, user2, guest1)
    }

    @PostConstruct
    fun enableAuthCtxOnSpawnedThreads() {
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL)
    }
}