package com.demo.react.server.runtime;

public enum Element {

    GAME("Game");

    private String html;
    private String tag;

    Element(String tag) {
        this.tag = tag;
        this.html = "<" + getTag() + "/>";
    }

    @Override
    public String toString() {
        return getHtml();
    }

    public String getHtml() {
        return this.html;
    }

    public String getTag() {
        return this.tag;
    }

}
