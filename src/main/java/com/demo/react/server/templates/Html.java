package com.demo.react.server.templates;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Html {

    private String left;
    private String right;

    public Html() {
        InputStream in = getClass().getClassLoader().getResourceAsStream("static/index.html");
        Scanner s = new Scanner(in).useDelimiter("\\A");
        String index = s.hasNext() ? s.next() : "";
        String regex = "[\\s\\S]+<div id=\"root\">";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(index);
        matcher.find();
        left = matcher.group(0);
        String[] split = index.split(regex);
        right = split[1];
    }

    public String getIndex(String element) {
        return left + element + right;
    }

}
