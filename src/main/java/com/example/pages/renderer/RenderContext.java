package com.example.pages.renderer;

import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;

public class RenderContext {

    public final UICommandBuilder cmd;
    public final String sectionSelector;
    private int bodyIndex = 0;

    public RenderContext(UICommandBuilder cmd, String sectionSelector) {
        this.cmd = cmd;
        this.sectionSelector = sectionSelector;
    }

    public String appendToBody(String uiPath) {
        cmd.append(sectionSelector + " #SecBody", uiPath);
        return sectionSelector + " #SecBody[" + bodyIndex++ + "]";
    }
}
