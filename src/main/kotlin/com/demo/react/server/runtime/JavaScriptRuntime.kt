package com.demo.react.server.runtime

import com.demo.react.server.runtime.impl.RenderState
import org.springframework.stereotype.Component

@Component
class JavaScriptRuntime(private val engine: RenderState) : RenderState {

    override fun babelTransform(reactSnippet: String) = engine.babelTransform(reactSnippet)

    override fun identity() = engine.identity()

    override fun eval(script: String) = engine.eval(script)

    operator fun get(element: Element, materialUI: Boolean): String {
        return if (materialUI) {
            render(element)
        } else {
            renderSimple(element)
        }
    }

    private fun renderSimple(element: Element) = eval(babelTransform("renderToString(${element.html()})"))

    private fun render(element: Element) = eval(babelTransform("""
//        var sheetsRegistry = new SheetsRegistry();
        renderToString(
//            <JssProvider registry={sheetsRegistry} generateClassName={createGenerateClassName()}>
//                <MuiThemeProvider theme={theme} sheetsManager={new Map()}>
                    ${element.html()}
//                </MuiThemeProvider>
//            </JssProvider>
        );
    """))

    fun css() = eval("sheetsRegistry.toString()")

}
