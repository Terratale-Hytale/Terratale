package com.example.pages.renderer.inline;

import com.example.content.inline.InlineElement;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;

import java.util.List;

public final class InlineRenderer {

    private static final List<InlineRenderRule<?>> RULES = List.of(
            new TextInlineRenderer(),
            new BoldInlineRenderer(),
            new ItalicInlineRenderer()
    );

    @SuppressWarnings("unchecked")
    public static void render(
            InlineElement inline,
            UICommandBuilder cmd,
            String paragraphSelector,
            int index
    ) {
        for (InlineRenderRule<?> rule : RULES) {
            if (rule.supports().isInstance(inline)) {
                ((InlineRenderRule<InlineElement>) rule)
                        .render(inline, cmd, paragraphSelector, index);
                return;
            }
        }
    }
}
