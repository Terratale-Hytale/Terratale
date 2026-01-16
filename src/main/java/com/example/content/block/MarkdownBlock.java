package com.example.content.block;

public sealed interface MarkdownBlock
        permits ParagraphBlock, LineBreakBlock {}
