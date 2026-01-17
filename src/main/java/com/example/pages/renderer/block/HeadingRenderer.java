package com.example.pages.renderer.block;

import com.example.content.block.HeadingBlock;
import com.example.pages.renderer.BlockRenderer;
import com.example.pages.renderer.RenderContext;

public class HeadingRenderer implements BlockRenderer<HeadingBlock> {

    @Override
    public Class<HeadingBlock> supports() {
        return HeadingBlock.class;
    }

    @Override
    public void render(HeadingBlock heading, RenderContext ctx) {

        String uiPath = switch (heading.level()) {
            case 2 -> "Pages/blocks/Heading2.ui";
            case 3 -> "Pages/blocks/Heading3.ui";
            case 4 -> "Pages/blocks/Heading4.ui";
            default -> null;
        };

        if (uiPath == null) return;

        String selector = ctx.appendToBody(uiPath);
        ctx.cmd.set(selector + " #Text.Text", heading.text());
    }
}
