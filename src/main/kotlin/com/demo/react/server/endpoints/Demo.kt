package com.demo.react.server.endpoints

import com.demo.react.server.runtime.Element
import com.demo.react.server.runtime.JavaScriptRuntime
import com.demo.react.server.templates.Html
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class Demo(
        private val runtime: JavaScriptRuntime,
        private val html: Html
) {

    @GetMapping
    fun getRoot() = html.getIndex(runtime[Element.GAME])

    @GetMapping("id")
    fun getIdentity() = runtime.identity()

}
