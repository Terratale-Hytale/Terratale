package com.example.content.parser;

import com.example.content.MarkdownSection;
import com.example.content.block.MarkdownBlock;

import java.util.ArrayList;
import java.util.List;

public class ParserContext {

    public String currentTitle;
    public List<MarkdownBlock> currentBlocks = new ArrayList<>();
    public List<MarkdownSection> sections = new ArrayList<>();

    public void flushSection() {
        if (currentTitle != null) {
            sections.add(new MarkdownSection(currentTitle, currentBlocks));
            currentBlocks = new ArrayList<>();
        }
    }
}
