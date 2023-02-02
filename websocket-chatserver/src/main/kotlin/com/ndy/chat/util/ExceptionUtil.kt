package com.ndy.chat.util

import kotlin.reflect.KClass

fun <T : Any> notFound(entity: KClass<T>, id: String): Nothing = fail("no such ${entity.simpleName} id : $id")

private fun fail(message: String): Nothing = throw IllegalArgumentException(message)