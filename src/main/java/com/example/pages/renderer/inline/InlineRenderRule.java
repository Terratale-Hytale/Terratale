package com.example.pages.renderer.inline;

import com.example.content.inline.InlineElement;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;

public interface InlineRenderRule<T extends InlineElement> {

    Class<T> supports();

    void render(
            T inline,
            UICommandBuilder cmd,
            String paragraphSelector,
            int index
    );
}
