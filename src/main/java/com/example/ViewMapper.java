package com.example;

import com.example.content.MarkdownLoader;
import com.example.content.MarkdownSection;
import com.example.content.parser.MarkdownParser;
import com.example.pages.navigation.MarkdownView;
import com.example.plugin.TerratalePlugin;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public class ViewMapper {
    private HashMap<String, List<MarkdownSection>> pages = new HashMap<>();

    public ViewMapper(List<MarkdownView> views) {
        try {
            for(int i = 0; i < views.size(); i++) {
                MarkdownView view = views.get(i);

                List<String> lines = MarkdownLoader.loadLines(view.markdownPath());
                List<MarkdownSection> sections = MarkdownParser.parse(lines);

                pages.put(view.id(), sections);
            }
        } catch (Exception e) {
            TerratalePlugin.get().getLogger().at(Level.SEVERE).log("Error loading markdown files.");
            TerratalePlugin.get().getLogger().at(Level.SEVERE).log(e.getMessage());
        }
    }

    public List<MarkdownSection> getPage(String id) {
        return pages.get(id);
    }

    public HashMap<String, List<MarkdownSection>> getPages() {
        return pages;
    }
}
