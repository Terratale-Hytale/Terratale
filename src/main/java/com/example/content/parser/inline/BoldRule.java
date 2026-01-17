package com.example.content.parser.inline;

import com.example.content.inline.BoldInline;

public class BoldRule implements InlineRule {

    @Override
    public boolean matches(String text, int i) {
        return text.startsWith("**", i);
    }

    @Override
    public InlineParseResult apply(String text, int i) {
        int end = text.indexOf("**", i + 2);
        if (end == -1) return null;

        return new InlineParseResult(
                new BoldInline(text.substring(i + 2, end)),
                end + 2
        );
    }
}
