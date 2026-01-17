package com.example.pages.renderer.block;

import com.example.content.block.SeparatorBlock;
import com.example.pages.renderer.BlockRenderer;
import com.example.pages.renderer.RenderContext;

public class SeparatorRenderer implements BlockRenderer<SeparatorBlock> {

    @Override
    public Class<SeparatorBlock> supports() {
        return SeparatorBlock.class;
    }

    @Override
    public void render(SeparatorBlock block, RenderContext ctx) {
        ctx.appendToBody("Pages/blocks/Separator.ui");
    }
}
