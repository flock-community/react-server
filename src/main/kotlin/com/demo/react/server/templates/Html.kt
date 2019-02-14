package com.demo.react.server.templates

import com.demo.react.server.utils.Utils.read
import org.springframework.stereotype.Component

@Component
class Html {

    private val indexGame = read("static/index.game.html")
    private val indexApp = read("static/index.app.html")
    private val regex = Regex("[\\s\\S]+<div id=\"root\">")

    fun getSimpleIndex(element: String) = html(indexGame, element)

    fun getMaterialUIindex(element: String) = html(indexApp, element)

    private fun html(index: String, body: String) = "${regex.find(index)?.value}$body${regex.split(index)[1]}"

}
