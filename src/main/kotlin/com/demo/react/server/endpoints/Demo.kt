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

    @GetMapping("game")
    fun getRoot() = html.getSimpleIndex(runtime[Element.GAME, false])

    @GetMapping("game/client")
    fun getClient() = html.getSimpleIndex("")

    @GetMapping("app")
    fun getApp() = html.getMaterialUIindex(runtime[Element.APP, true])

    @GetMapping("id")
    fun getIdentity() = runtime.identity()

}
