package com.example.content.parser;

import com.example.content.MarkdownSection;
import com.example.content.parser.block.*;

import java.util.List;

public final class MarkdownParser {

    private static final List<BlockRule> RULES = List.of(
            new H1Rule(),
            new HeadingRule(),
            new SeparatorRule(),
            new BlankLineRule(),
            new ParagraphRule()
    );

    public static List<MarkdownSection> parse(List<String> lines) {

        ParserContext ctx = new ParserContext();

        for (String line : lines) {
            for (BlockRule rule : RULES) {
                if (rule.matches(line)) {
                    rule.apply(line, ctx);
                    break;
                }
            }
        }

        ctx.flushSection();
        return ctx.sections;
    }
}
