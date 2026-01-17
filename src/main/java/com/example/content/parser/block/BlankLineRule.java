package com.example.content.parser.block;

import com.example.content.block.LineBreakBlock;
import com.example.content.parser.ParserContext;

public class BlankLineRule implements BlockRule {

    @Override
    public boolean matches(String line) {
        return line.isBlank();
    }

    @Override
    public void apply(String line, ParserContext ctx) {
        ctx.currentBlocks.add(new LineBreakBlock());
    }
}
