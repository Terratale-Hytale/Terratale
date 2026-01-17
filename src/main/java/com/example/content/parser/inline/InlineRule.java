package com.example.content.parser.inline;

public interface InlineRule {
    boolean matches(String text, int index);
    InlineParseResult apply(String text, int index);
}
