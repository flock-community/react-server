package com.demo.react.server.runtime

enum class Element(val tag: String) {

    GAME("Game");

    val html: String = "<$tag/>"

    override fun toString() = html

}
