package com.demo.react.server.runtime

import org.springframework.stereotype.Component

@Component
class JavaScriptRuntime(private val engine: RenderState) : RenderState {

    override operator fun get(element: Element) = engine.render(element)

    override fun render(element: Element) = engine.render(element)

    override fun babelTransform(reactSnippet: String) = engine.babelTransform(reactSnippet)

    override fun identity() = engine.identity()

}
