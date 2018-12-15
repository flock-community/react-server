package com.demo.react.server.templates

import com.demo.react.server.utils.Utils.read
import org.springframework.stereotype.Component

import java.util.Scanner
import java.util.regex.Pattern

@Component
class Html {

    private var left = ""
    private var right = ""

    init {
        val index = read("static/index.html")
        val regex = "[\\s\\S]+<div id=\"root\">"

        val matcher = Pattern.compile(regex).matcher(index)
        if (matcher.find()) {
            left = matcher.group(0)
            right = index.split(regex.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
        }

    }

    fun getIndex(element: String) = "$left$element$right"

}
