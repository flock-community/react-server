package com.demo.react.server.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demo {

    @GetMapping("/demo")
    public String getDemo() {
        return "<html>Hello World</html>";
    }

}
