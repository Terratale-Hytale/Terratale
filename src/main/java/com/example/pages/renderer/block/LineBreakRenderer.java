package com.example.pages.renderer.block;

import com.example.content.block.LineBreakBlock;
import com.example.pages.renderer.BlockRenderer;
import com.example.pages.renderer.RenderContext;

public class LineBreakRenderer implements BlockRenderer<LineBreakBlock> {

    @Override
    public Class<LineBreakBlock> supports() {
        return LineBreakBlock.class;
    }

    @Override
    public void render(LineBreakBlock block, RenderContext ctx) {
        ctx.appendToBody("Pages/blocks/LineBreak.ui");
    }
}
