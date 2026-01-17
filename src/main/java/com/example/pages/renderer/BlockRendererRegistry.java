package com.example.pages.renderer;

import com.example.content.block.MarkdownBlock;
import com.example.pages.renderer.block.*;

import java.util.List;

public final class BlockRendererRegistry {

    private static final List<BlockRenderer<?>> RENDERERS = List.of(
            new HeadingRenderer(),
            new LineBreakRenderer(),
            new SeparatorRenderer(),
            new ParagraphRenderer()
    );

    @SuppressWarnings("unchecked")
    public static void render(MarkdownBlock block, RenderContext ctx) {
        for (BlockRenderer<?> renderer : RENDERERS) {
            if (renderer.supports().isInstance(block)) {
                ((BlockRenderer<MarkdownBlock>) renderer).render(block, ctx);
                return;
            }
        }
    }
}
