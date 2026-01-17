package com.example.pages;

import com.example.content.MarkdownLoader;
import com.example.content.MarkdownSection;
import com.example.content.block.MarkdownBlock;
import com.example.content.parser.MarkdownParser;
import com.example.pages.navigation.MarkdownView;
import com.example.pages.renderer.BlockRendererRegistry;
import com.example.pages.renderer.RenderContext;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;
import java.util.List;

public class TestPage extends InteractiveCustomUIPage<TestPage.EventData> {

    private String id = "";

    public static class EventData {
        public static final BuilderCodec<EventData> CODEC =
                BuilderCodec.builder(EventData.class, EventData::new).build();
    }

    private final List<MarkdownView> views;


    public TestPage(
            PlayerRef playerRef,
            List<MarkdownView> sections
    ) {
        super(
                playerRef,
                CustomPageLifetime.CanDismissOrCloseThroughInteraction,
                EventData.CODEC
        );
        this.views = sections;
    }

    @Override
    public void build(
            @Nonnull Ref<EntityStore> ref,
            @Nonnull UICommandBuilder cmd,
            @Nonnull UIEventBuilder evt,
            @Nonnull Store<EntityStore> store
    ) {
        cmd.append("Pages/Test.ui");

        try {
            MarkdownView view = views.get(1);

            List<String> lines = MarkdownLoader.loadLines(view.markdownPath());
            List<MarkdownSection> sections = MarkdownParser.parse(lines);

            for (int i = 0; i < sections.size(); i++) {
                MarkdownSection section = sections.get(i);

                cmd.append("#Container #ContentList", "Pages/MarkdownSection.ui");
                String sectionSelector = "#ContentList[" + i + "]";

                cmd.set(sectionSelector + " #SecTitle.Text", section.title());

                RenderContext ctx = new RenderContext(cmd, sectionSelector);

                for (MarkdownBlock block : section.blocks()) {
                    BlockRendererRegistry.render(block, ctx);
                }
            }
        }
        catch (Exception e) {

        }
    }



}
