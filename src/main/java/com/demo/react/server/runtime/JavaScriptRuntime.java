package com.demo.react.server.runtime;

import jdk.nashorn.api.scripting.NashornScriptEngine;
import org.springframework.stereotype.Component;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

@Component
public class JavaScriptRuntime {

    private NashornScriptEngine nashorn = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");

    public JavaScriptRuntime() {
        try {
            nashorn.eval("var global = this; var module = {}; var console = {}; console.debug = print; console.warn = print; console.error = print; console.log = print;");
            nashorn.eval(read("vendor/polyfill.js"));
            nashorn.eval(read("static/server.bundle.js"));
            nashorn.eval("var React = module.exports.React; var renderToString = module.exports.renderToString; var Game = module.exports.Game;");
            nashorn.eval("function render() { return renderToString(React.createElement(Game, null)); }");

        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public String get() {
        return render();
    }

    private String render() {
        String answer = "";
        try {
            answer = (String) nashorn.invokeFunction("render");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer;
    }

    private Reader read(String path) {
        InputStream in = getClass().getClassLoader().getResourceAsStream(path);
        return new InputStreamReader(in);
    }

}
