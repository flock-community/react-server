package com.demo.react.server.runtime

import com.demo.react.server.utils.Utils.read
import org.graalvm.polyglot.Context
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import javax.script.ScriptException

@Component
@Profile("graal")
class Graal : RenderState {

    private val context = Context.create("js")
    private val jsBindings = context.getBindings("js")


    init {
        context.eval("js", "var global = this; var module = {}; var console = {}; console.debug = print; console.warn = print; console.error = print; console.log = print;")
        context.eval("js", read("vendor/polyfill.js"))
        context.eval("js", read("vendor/babel.js"))
        context.eval("js", read("static/server.bundle.js"))
        context.eval("js", "var React = module.exports.React; var renderToString = module.exports.renderToString; var " + Element.GAME.tag + " = module.exports." + Element.GAME.tag + ";")
    }

    override operator fun get(element: Element) = render(element)

    override fun render(element: Element): String = try {
        context.eval("js", babelTransform("renderToString(${element.html});")).asString()
    } catch (e: ScriptException) {
        e.printStackTrace()
        ""
    }

    override fun babelTransform(reactSnippet: String): String = try {
        jsBindings.putMember("input", reactSnippet)
        context.eval("js", "Babel.transform(input, { presets: ['react' ] }).code").asString()
    } catch (e: ScriptException) {
        e.printStackTrace()
        ""
    }

    override fun identity() = "Graal VM"

}
