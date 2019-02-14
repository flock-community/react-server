package com.demo.react.server.runtime

enum class Element(
        val tag: String
) {

    GAME("Game"),
    APP("App");

    fun html(): String = "<$tag/>"

    override fun toString() = html()

}
