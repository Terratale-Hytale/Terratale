package com.example.content.parser.block;

import com.example.content.block.SeparatorBlock;
import com.example.content.parser.ParserContext;

public class SeparatorRule implements BlockRule {

    @Override
    public boolean matches(String line) {
        return line.trim().equals("---");
    }

    @Override
    public void apply(String line, ParserContext ctx) {
        ctx.currentBlocks.add(new SeparatorBlock());
    }
}
