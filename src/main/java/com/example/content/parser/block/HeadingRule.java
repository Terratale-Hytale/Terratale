package com.example.content.parser.block;

import com.example.content.block.HeadingBlock;
import com.example.content.parser.ParserContext;

public class HeadingRule implements BlockRule {

    @Override
    public boolean matches(String line) {
        return line.startsWith("## ");
    }

    @Override
    public void apply(String line, ParserContext ctx) {
        int level = 0;
        while (level < line.length() && line.charAt(level) == '#') {
            level++;
        }
        ctx.currentBlocks.add(
                new HeadingBlock(level, line.substring(level + 1).trim())
        );
    }
}
