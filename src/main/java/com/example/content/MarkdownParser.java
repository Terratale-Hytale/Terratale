package com.example.content;

import com.example.content.block.*;
import com.example.content.inline.*;

import java.util.ArrayList;
import java.util.List;

public final class MarkdownParser {

    private MarkdownParser() {}

    public static List<MarkdownSection> parse(List<String> lines) {
        List<MarkdownSection> sections = new ArrayList<>();

        String currentTitle = null;
        List<MarkdownBlock> currentBlocks = new ArrayList<>();

        for (String line : lines) {

            // ---------- H1 ----------
            if (line.startsWith("# ")) {
                if (currentTitle != null) {
                    sections.add(new MarkdownSection(currentTitle, currentBlocks));
                    currentBlocks = new ArrayList<>();
                }
                currentTitle = line.substring(2).trim();
                continue;
            }

            // ---------- H2 / H3 / H4 ----------
            if (line.startsWith("## ")) {
                currentBlocks.add(new HeadingBlock(2, line.substring(3).trim()));
                continue;
            }

            if (line.startsWith("### ")) {
                currentBlocks.add(new HeadingBlock(3, line.substring(4).trim()));
                continue;
            }

            if (line.startsWith("#### ")) {
                currentBlocks.add(new HeadingBlock(4, line.substring(5).trim()));
                continue;
            }

            // ---------- SALTO ----------
            if (line.isBlank()) {
                currentBlocks.add(new LineBreakBlock());
                continue;
            }

            // ---------- SEPARADOR ----------
            if (line.trim().equals("---")) {
                currentBlocks.add(new SeparatorBlock());
                continue;
            }

            // ---------- PÁRRAFO ----------
            currentBlocks.add(
                    new ParagraphBlock(parseInline(line))
            );
        }

        if (currentTitle != null) {
            sections.add(new MarkdownSection(currentTitle, currentBlocks));
        }

        return sections;
    }


    private static List<InlineElement> parseInline(String line) {
        List<InlineElement> result = new ArrayList<>();

        int i = 0;
        while (i < line.length()) {
            if (line.startsWith("**", i)) {
                int end = line.indexOf("**", i + 2);
                if (end != -1) {
                    result.add(new BoldInline(line.substring(i + 2, end)));
                    i = end + 2;
                    continue;
                }
            }

            int next = line.indexOf("**", i);
            if (next == -1) next = line.length();

            result.add(new TextInline(line.substring(i, next)));
            i = next;
        }

        return result;
    }
}
