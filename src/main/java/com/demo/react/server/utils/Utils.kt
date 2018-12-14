package com.demo.react.server.utils

object Utils {

    fun read(path: String): String {
        val resource = this::class.java.classLoader.getResource(path) ?: return ""
        return resource.readText()
    }

}
