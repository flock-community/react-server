package com.demo.react.server.runtime.impl

import com.demo.react.server.runtime.Element
import com.demo.react.server.runtime.Element.APP
import com.demo.react.server.runtime.Element.GAME

abstract class AbstractEngine : RenderState {

    val global = "var global = this; var module = {}; var console = {}; console.debug = print; console.warn = print; console.error = print; console.log = print;"

    val polyfill = "vendor/polyfill.js"

    val babel = "vendor/babel.js"

    val serverBundle = "static/server.bundle.js"

    val babelTransform = "Babel.transform(input, { presets: ['react' ] }).code"

    val init: String = """
            var React = module.exports.React;
            var renderToString = module.exports.renderToString;
            var SheetsRegistry = module.exports.SheetsRegistry;
            var JssProvider = module.exports.JssProvider;
            var MuiThemeProvider = module.exports.MuiThemeProvider;
            var createGenerateClassName = module.exports.createGenerateClassName;
            var theme = module.exports.theme;
            ${import(GAME)}
            ${import(APP)}
        """.trimIndent()

    fun import(element: Element): String = "var ${element.tag} = module.exports.${element.tag};"

}
