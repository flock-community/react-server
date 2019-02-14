package com.demo.react.server.runtime.impl

interface RenderState {

    fun identity(): String

    fun eval(script: String): String

    fun babelTransform(reactSnippet: String): String

}
