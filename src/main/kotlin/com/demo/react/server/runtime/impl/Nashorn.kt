package com.demo.react.server.runtime.impl

import com.demo.react.server.utils.Utils.read
import jdk.nashorn.api.scripting.NashornScriptEngine
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import javax.script.ScriptEngineManager
import javax.script.ScriptException
import javax.script.SimpleBindings

@Component
@Profile("!graal")
class Nashorn : AbstractEngine() {

    private val nashorn = ScriptEngineManager().getEngineByName("nashorn") as NashornScriptEngine
    private val babelBindings = SimpleBindings()

    init {
        try {
            nashorn.eval(global)
            nashorn.eval(read(polyfill))
            nashorn.eval(read(babel), babelBindings)
            nashorn.eval(read(serverBundle))
            nashorn.eval(init)
        } catch (e: ScriptException) {
            e.printStackTrace()
        }
    }

    override fun identity() = "Nashorn"

    override fun eval(script: String): String = try {
        nashorn.eval(script) as String
    } catch (e: ScriptException) {
        e.printStackTrace()
        ""
    }

    override fun babelTransform(reactSnippet: String) = try {
        babelBindings["input"] = reactSnippet
        nashorn.eval(babelTransform, babelBindings) as String
    } catch (e: ScriptException) {
        e.printStackTrace()
        ""
    }

}
