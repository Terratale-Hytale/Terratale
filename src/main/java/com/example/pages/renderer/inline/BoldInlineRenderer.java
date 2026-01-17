package com.example.pages.renderer.inline;

import com.example.content.inline.BoldInline;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;

public class BoldInlineRenderer implements InlineRenderRule<BoldInline> {

    @Override
    public Class<BoldInline> supports() {
        return BoldInline.class;
    }

    @Override
    public void render(
            BoldInline inline,
            UICommandBuilder cmd,
            String paragraphSelector,
            int index
    ) {
        cmd.append(paragraphSelector, "Pages/inline/Bold.ui");
        cmd.set(
                paragraphSelector + "[" + index + "] #Text.Text",
                inline.text()
        );
    }
}
