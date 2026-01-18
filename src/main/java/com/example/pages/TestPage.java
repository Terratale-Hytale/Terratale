package com.example.pages;

import com.example.content.MarkdownLoader;
import com.example.content.MarkdownSection;
import com.example.content.block.MarkdownBlock;
import com.example.content.parser.MarkdownParser;
import com.example.pages.navigation.MarkdownView;
import com.example.pages.renderer.BlockRendererRegistry;
import com.example.pages.renderer.RenderContext;
import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.EventData;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;


import javax.annotation.Nonnull;
import java.util.List;

public class TestPage extends InteractiveCustomUIPage<TestPage.UIEventPayload> {

    private String id = "";

    public static class UIEventPayload {

        public String id;

        public static final BuilderCodec<UIEventPayload> CODEC = ((BuilderCodec.Builder<UIEventPayload>) (BuilderCodec.Builder<UIEventPayload>)
                BuilderCodec.builder(UIEventPayload.class, UIEventPayload::new)
                        .append(new KeyedCodec<>("ID", Codec.STRING),
                                (UIEventPayload o, String v) -> o.id = v,
                                (UIEventPayload o) -> o.id
                        )
                        .add()).build();
    }

    private final List<MarkdownView> views;
    private String currentViewId = "";


    public TestPage(
            PlayerRef playerRef,
            List<MarkdownView> views
    ) {
        super(
                playerRef,
                CustomPageLifetime.CanDismissOrCloseThroughInteraction,
                UIEventPayload.CODEC
        );
        this.views = views;
        this.currentViewId = views.getFirst().id();
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
            MarkdownView view = views.stream()
                    .filter(v -> v.id().equals(currentViewId))
                    .findFirst()
                    .orElse(null);

            if (view == null) {
                return;
            }

            for (int i = 0; i < views.size(); i++) {

                MarkdownView currentView = views.get(i);
                EventData uiData = new EventData();
                uiData.events().put("ID", currentView.id());

                cmd.append("#Container #Navbar", "Pages/NavItem.ui");
                String navbarItem = "#Navbar[" + i + "]";
                cmd.set(navbarItem + " #Btn.Text", currentView.title());
                evt.addEventBinding(
                        CustomUIEventBindingType.Activating,
                        navbarItem + " #Btn",
                        uiData,
                        false
                );
                cmd.set(navbarItem + " #Btn.Text", currentView.title());
            }

            renderContentInto(cmd, evt, currentViewId);
        }
        catch (Exception e) {

        }
    }

    @Override
    public void handleDataEvent(
            @Nonnull Ref<EntityStore> ref,
            @Nonnull Store<EntityStore> store,
            @Nonnull UIEventPayload data
    ) {
        playerRef.sendMessage(Message.raw("Evento recibido"));
        playerRef.sendMessage(Message.raw("Boton pulsado: " + data.id));

        String clickedId = data.id;
        if (clickedId == null || clickedId.isBlank()) return;
        if (clickedId.equals(currentViewId)) return;

        boolean exists = views.stream().anyMatch(v -> v.id().equals(clickedId));
        if (!exists) return;

        currentViewId = clickedId;

        UICommandBuilder updateCmd = new UICommandBuilder();
        UIEventBuilder updateEvt = new UIEventBuilder();

        updateCmd.clear("#ContentList");

        renderContentInto(updateCmd, updateEvt, currentViewId);

        sendUpdate(updateCmd, updateEvt, false);
    }

    private void renderContentInto(UICommandBuilder cmd, UIEventBuilder evt, String viewId) {
        try {
            MarkdownView view = views.stream()
                    .filter(v -> v.id().equals(viewId))
                    .findFirst()
                    .orElse(null);

            if (view == null) return;

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
        } catch (Exception ignored) {
        }
    }
}
