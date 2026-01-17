package com.example.content.parser.block;

import com.example.content.parser.ParserContext;

public interface BlockRule {
    boolean matches(String line);
    void apply(String line, ParserContext ctx);
}
