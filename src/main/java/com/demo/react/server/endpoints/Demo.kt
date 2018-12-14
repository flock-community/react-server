package com.demo.react.server.endpoints

import com.demo.react.server.runtime.Element
import com.demo.react.server.runtime.JavaScriptRuntime
import com.demo.react.server.templates.Html
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Demo(
        private val runtime: JavaScriptRuntime,
        private val html: Html
) {

    @GetMapping("/")
    fun getDemo(): String {
        val body = runtime[Element.GAME]
        return html.getIndex(body)
    }

}
