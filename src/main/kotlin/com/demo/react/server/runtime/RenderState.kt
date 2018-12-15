package com.demo.react.server.runtime

interface RenderState {

    operator fun get(element: Element): String

    fun render(element: Element): String

    fun babelTransform(reactSnippet: String): String

    fun identity(): String

}
