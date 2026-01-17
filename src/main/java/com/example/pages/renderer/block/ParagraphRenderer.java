package com.example.pages.renderer.block;

import com.example.content.block.ParagraphBlock;
import com.example.content.inline.InlineElement;
import com.example.pages.renderer.BlockRenderer;
import com.example.pages.renderer.RenderContext;
import com.example.pages.renderer.inline.InlineRenderer;

public class ParagraphRenderer implements BlockRenderer<ParagraphBlock> {

    @Override
    public Class<ParagraphBlock> supports() {
        return ParagraphBlock.class;
    }

    @Override
    public void render(ParagraphBlock paragraph, RenderContext ctx) {

        String paragraphSelector =
                ctx.appendToBody("Pages/blocks/Paragraph.ui");

        int index = 0;
        for (InlineElement inline : paragraph.inlines()) {
            InlineRenderer.render(
                    inline,
                    ctx.cmd,
                    paragraphSelector,
                    index++
            );
        }
    }
}
