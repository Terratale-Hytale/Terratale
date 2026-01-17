package com.example.pages.navigation;

import java.nio.file.Path;

public record MarkdownView(
        String id,
        String title,
        Path markdownPath
) {}
