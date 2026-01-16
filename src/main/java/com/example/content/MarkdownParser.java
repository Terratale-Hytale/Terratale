package com.example.content;

import java.util.ArrayList;
import java.util.List;

public final class MarkdownParser {

    private MarkdownParser() {}

    public static List<MarkdownSection> parse(List<String> lines) {
        List<MarkdownSection> result = new ArrayList<>();

        String currentTitle = null;
        StringBuilder body = new StringBuilder();

        for (String line : lines) {
            if (line.startsWith("#")) {
                if (currentTitle != null) {
                    result.add(new MarkdownSection(
                            currentTitle,
                            body.toString().trim()
                    ));
                    body.setLength(0);
                }
                currentTitle = line.substring(1).trim();
            } else {
                body.append(line).append("\n");
            }
        }

        if (currentTitle != null) {
            result.add(new MarkdownSection(
                    currentTitle,
                    body.toString().trim()
            ));
        }

        return result;
    }
}
