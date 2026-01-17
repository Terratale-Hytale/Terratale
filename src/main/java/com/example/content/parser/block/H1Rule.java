package com.example.content.parser.block;

import com.example.content.parser.ParserContext;

public class H1Rule implements BlockRule {

    @Override
    public boolean matches(String line) {
        return line.startsWith("# ");
    }

    @Override
    public void apply(String line, ParserContext ctx) {
        ctx.flushSection();
        ctx.currentTitle = line.substring(2).trim();
    }
}
