package com.example.pages.renderer.inline;

import com.example.content.inline.TextInline;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;

public class TextInlineRenderer implements InlineRenderRule<TextInline> {

    @Override
    public Class<TextInline> supports() {
        return TextInline.class;
    }

    @Override
    public void render(
            TextInline inline,
            UICommandBuilder cmd,
            String paragraphSelector,
            int index
    ) {
        cmd.append(paragraphSelector, "Pages/inline/Text.ui");
        cmd.set(
                paragraphSelector + "[" + index + "] #Text.Text",
                inline.text()
        );
    }
}
