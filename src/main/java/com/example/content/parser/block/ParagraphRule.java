package com.example.content.parser.block;

import com.example.content.block.ParagraphBlock;
import com.example.content.parser.ParserContext;
import com.example.content.parser.inline.InlineParser;

public class ParagraphRule implements BlockRule {

    @Override
    public boolean matches(String line) {
        return true;
    }

    @Override
    public void apply(String line, ParserContext ctx) {
        ctx.currentBlocks.add(
                new ParagraphBlock(InlineParser.parse(line))
        );
    }
}
