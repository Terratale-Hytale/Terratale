package com.example.content.parser.inline;

import com.example.content.inline.InlineElement;

public record InlineParseResult(
        InlineElement element,
        int nextIndex
) {}
