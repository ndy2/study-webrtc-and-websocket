package com.ndy.chat.config.websocket

import com.ndy.chat.config.jwt.JwtTokenDecoder
import org.springframework.core.MethodParameter
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.messaging.Message
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.security.core.annotation.AuthenticationPrincipal

class WebSocketAuthenticationPrincipalArgumentResolver(
    private val jwtTokenDecoder: JwtTokenDecoder
) : HandlerMethodArgumentResolver {


    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return findMethodAnnotation(AuthenticationPrincipal::class.java, parameter) != null
    }

    override fun resolveArgument(parameter: MethodParameter, message: Message<*>): Any? {
        val token = StompHeaderAccessor.getFirstNativeHeader("token", message.headers)!!
        return jwtTokenDecoder.decode(token)
    }

    private fun <T : Annotation?> findMethodAnnotation(annotationClass: Class<T>, parameter: MethodParameter): T? {
        var annotation = parameter.getParameterAnnotation(annotationClass)
        if (annotation != null) {
            return annotation
        }
        val annotationsToSearch = parameter.parameterAnnotations
        for (toSearch in annotationsToSearch) {
            annotation = AnnotationUtils.findAnnotation<T>(toSearch.annotationClass::class.java, annotationClass)
            if (annotation != null) {
                return annotation
            }
        }
        return null
    }
}