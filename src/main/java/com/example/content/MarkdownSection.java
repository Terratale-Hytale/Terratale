package com.example.content;

public class MarkdownSection {

    private final String title;
    private final String body;

    public MarkdownSection(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String title() {
        return title;
    }

    public String body() {
        return body;
    }
}
