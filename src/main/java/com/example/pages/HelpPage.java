package com.example.pages;

import com.example.content.MarkdownSection;
import com.example.plugin.TerratalePlugin;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.logging.Level;

public class HelpPage extends InteractiveCustomUIPage<HelpPage.OnClickEventData> {

    public static class OnClickEventData {
        public static final BuilderCodec<OnClickEventData> CODED =
                BuilderCodec.builder(OnClickEventData.class, OnClickEventData::new).build();
    }

    private List<MarkdownSection> sections;

    public HelpPage(
            @NonNullDecl PlayerRef playerRef,
            List<MarkdownSection> sections
    ) {
        super(playerRef, CustomPageLifetime.CanDismissOrCloseThroughInteraction, OnClickEventData.CODED);
        this.sections = sections;
    }

    @Override
    public void build(
            @Nonnull Ref<EntityStore> ref,
            @Nonnull UICommandBuilder cmd,
            @Nonnull UIEventBuilder evt,
            @Nonnull Store<EntityStore> store
    ) {
        cmd.append("Pages/Rules.ui");

        for (int i = 0; i < sections.size(); i++) {
            MarkdownSection section = sections.get(i);
            cmd.append("#ContentList", "Pages/MarkdownSection.ui");
            String selector = "#ContentList[" + i + "]";
            cmd.set(selector + " #SecTitle.Text", section.title());
            cmd.set(selector + " #SecBody.Text", section.body());
        }
        evt.addEventBinding(
                CustomUIEventBindingType.Activating,
                "#RulesButton"
        );
    }

    @Override
    public void handleDataEvent(
            @Nonnull Ref<EntityStore> ref,
            @Nonnull Store<EntityStore> store,
            @Nonnull OnClickEventData data
    ) {
        UICommandBuilder update = new UICommandBuilder();
        sendUpdate(update);
    }
}


