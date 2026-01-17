package com.example.pages.renderer.inline;

import com.example.content.inline.ItalicInline;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;

public class ItalicInlineRenderer implements InlineRenderRule<ItalicInline> {

    @Override
    public Class<ItalicInline> supports() {
        return ItalicInline.class;
    }

    @Override
    public void render(
            ItalicInline inline,
            UICommandBuilder cmd,
            String paragraphSelector,
            int index
    ) {
        cmd.append(paragraphSelector, "Pages/inline/Italic.ui");
        cmd.set(
                paragraphSelector + "[" + index + "] #Text.Text",
                inline.text()
        );
    }
}
