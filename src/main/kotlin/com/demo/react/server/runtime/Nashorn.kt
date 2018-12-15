package com.demo.react.server.runtime

import com.demo.react.server.utils.Utils.read
import jdk.nashorn.api.scripting.NashornScriptEngine
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import javax.script.ScriptEngineManager
import javax.script.ScriptException
import javax.script.SimpleBindings

@Component
@Profile("!graal")
class Nashorn : RenderState {

    private val nashorn = ScriptEngineManager().getEngineByName("nashorn") as NashornScriptEngine
    private val babelBindings = SimpleBindings()

    init {
        try {
            nashorn.eval("var global = this; var module = {}; var console = {}; console.debug = print; console.warn = print; console.error = print; console.log = print;")
            nashorn.eval(read("vendor/polyfill.js"))
            nashorn.eval(read("vendor/babel.js"), babelBindings)
            nashorn.eval(read("static/server.bundle.js"))
            nashorn.eval("var React = module.exports.React; var renderToString = module.exports.renderToString; var ${Element.GAME.tag} = module.exports.${Element.GAME.tag};")
        } catch (e: ScriptException) {
            e.printStackTrace()
        }
    }

    override operator fun get(element: Element) = render(element)

    override fun render(element: Element): String {
        return try {
            nashorn.eval(babelTransform("renderToString(${element.html});")) as String
        } catch (e: ScriptException) {
            e.printStackTrace()
            ""
        }
    }

    override fun babelTransform(reactSnippet: String): String {
        return try {
            babelBindings["input"] = reactSnippet
            nashorn.eval("Babel.transform(input, { presets: ['react' ] }).code", babelBindings) as String
        } catch (e: ScriptException) {
            e.printStackTrace()
            ""
        }
    }

    override fun identity() = "Nashorn"

}
