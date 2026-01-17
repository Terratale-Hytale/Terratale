package com.example.content.parser.inline;

import com.example.content.inline.InlineElement;
import com.example.content.inline.TextInline;

import java.util.ArrayList;
import java.util.List;

public final class InlineParser {

    private static final List<InlineRule> RULES = List.of(
            new BoldRule(),
            new ItalicRule()
    );

    public static List<InlineElement> parse(String line) {

        List<InlineElement> result = new ArrayList<>();
        int i = 0;

        while (i < line.length()) {

            boolean matched = false;

            for (InlineRule rule : RULES) {
                if (rule.matches(line, i)) {
                    InlineParseResult r = rule.apply(line, i);
                    if (r != null) {
                        result.add(r.element());
                        i = r.nextIndex();
                        matched = true;
                        break;
                    }
                }
            }

            if (!matched) {
                result.add(new TextInline(String.valueOf(line.charAt(i))));
                i++;
            }
        }

        return result;
    }
}
