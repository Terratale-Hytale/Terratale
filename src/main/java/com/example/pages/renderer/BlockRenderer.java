package com.example.pages.renderer;

import com.example.content.block.MarkdownBlock;

public interface BlockRenderer<T extends MarkdownBlock> {

    Class<T> supports();

    void render(T block, RenderContext ctx);
}
