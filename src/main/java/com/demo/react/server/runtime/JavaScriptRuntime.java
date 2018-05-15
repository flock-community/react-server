package com.demo.react.server.runtime;

import jdk.nashorn.api.scripting.NashornScriptEngine;
import org.springframework.stereotype.Component;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

@Component
public class JavaScriptRuntime {

    private NashornScriptEngine nashorn = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
    private SimpleBindings babelBindings = new SimpleBindings();

    public JavaScriptRuntime() {
        try {
            nashorn.eval("var global = this; var module = {}; var console = {}; console.debug = print; console.warn = print; console.error = print; console.log = print;");
            nashorn.eval(read("vendor/polyfill.js"));
            nashorn.eval(read("vendor/babel.js"), babelBindings);
            nashorn.eval(read("static/server.bundle.js"));
            nashorn.eval("var React = module.exports.React; var renderToString = module.exports.renderToString; var " + Element.GAME.getTag() + " = module.exports." + Element.GAME.getTag() + ";");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public String get(Element element) {
        return render(element);
    }

    private String render(Element element) {
        try {
            return (String) nashorn.eval(babelTransform("renderToString(" + element.getHtml() + ");"));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String babelTransform(String reactSnippet) {
        try {
            babelBindings.put("input", reactSnippet);
            return (String) nashorn.eval("Babel.transform(input, { presets: ['react' ] }).code", babelBindings);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return "";
    }

    private Reader read(String path) {
        InputStream in = getClass().getClassLoader().getResourceAsStream(path);
        return new InputStreamReader(in);
    }

}
