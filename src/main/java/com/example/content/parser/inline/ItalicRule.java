package com.example.content.parser.inline;

import com.example.content.inline.ItalicInline;

public class ItalicRule implements InlineRule {

    @Override
    public boolean matches(String text, int i) {
        return text.startsWith("*", i) && !text.startsWith("**", i);
    }

    @Override
    public InlineParseResult apply(String text, int i) {
        int end = text.indexOf("*", i + 1);
        if (end == -1) return null;

        return new InlineParseResult(
                new ItalicInline(text.substring(i + 1, end)),
                end + 1
        );
    }
}
