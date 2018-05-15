package com.demo.react.server.endpoints;

import com.demo.react.server.runtime.Element;
import com.demo.react.server.runtime.JavaScriptRuntime;
import com.demo.react.server.templates.Html;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demo {

    private JavaScriptRuntime runtime;
    private Html html;

    @Autowired
    public demo(JavaScriptRuntime runtime, Html html) {
        this.runtime = runtime;
        this.html = html;
    }

    @GetMapping("/")
    public String getDemo() {
        String body = runtime.get(Element.GAME);
        return html.getIndex(body);
    }

}
