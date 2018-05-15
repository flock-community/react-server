package com.demo.react.server.templates;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Html {

    private String left = "";
    private String right = "";

    public Html() {
        String index = read("static/index.html");
        String regex = "[\\s\\S]+<div id=\"root\">";

        Matcher matcher = Pattern.compile(regex).matcher(index);
        if (matcher.find()) {
            left = matcher.group(0);
            right = index.split(regex)[1];
        }

    }

    public String getIndex(String element) {
        return left + element + right;
    }

    private String read(String path) {
        InputStream in = getClass().getClassLoader().getResourceAsStream(path);
        Scanner s = new Scanner(in).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
