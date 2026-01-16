package com.example.content.block;

import com.example.content.inline.InlineElement;
import java.util.List;

public final class QuoteBlock implements MarkdownBlock {

    private final int level; // 1 = >, 2 = >>, etc
    private final List<InlineElement> content;

    public QuoteBlock(int level, List<InlineElement> content) {
        this.level = level;
        this.content = content;
    }

    public int level() {
        return level;
    }

    public List<InlineElement> content() {
        return content;
    }
}
