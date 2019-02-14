package com.demo.react.server.runtime.impl

import com.demo.react.server.utils.Utils.read
import org.graalvm.polyglot.Context
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import javax.script.ScriptException

@Component
@Profile("graal")
class Graal : AbstractEngine() {

    private val context = Context.create("js")
    private val jsBindings = context.getBindings("js")

    init {
        context.eval("js", global)
        context.eval("js", read(polyfill))
        context.eval("js", read(babel))
        context.eval("js", read(serverBundle))
        context.eval("js", init)
    }

    override fun identity() = "Graal VM"

    override fun eval(script: String): String = try {
        context.eval("js", script).asString()
    } catch (e: ScriptException) {
        e.printStackTrace()
        ""
    }

    override fun babelTransform(reactSnippet: String): String = try {
        jsBindings.putMember("input", reactSnippet)
        context.eval("js", babelTransform).asString()
    } catch (e: ScriptException) {
        e.printStackTrace()
        ""
    }

}
