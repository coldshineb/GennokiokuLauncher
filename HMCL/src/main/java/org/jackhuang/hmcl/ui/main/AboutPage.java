/*
 * Hello Minecraft! Launcher
 * Copyright (C) 2022  huangyuhui <huanghongxun2008@126.com> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package org.jackhuang.hmcl.ui.main;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.jackhuang.hmcl.Metadata;
import org.jackhuang.hmcl.ui.FXUtils;
import org.jackhuang.hmcl.ui.construct.ComponentList;
import org.jackhuang.hmcl.ui.construct.IconedTwoLineListItem;

import static org.jackhuang.hmcl.util.i18n.I18n.i18n;

public final class AboutPage extends StackPane {

    public AboutPage() {
        ComponentList about = new ComponentList();
        {
            IconedTwoLineListItem launcher = new IconedTwoLineListItem();
            launcher.setImage(FXUtils.newBuiltinImage("/assets/img/gennokioku.png", 32, 32, false, true));
            launcher.setTitle("Gennokioku 启动器");
            launcher.setSubtitle(Metadata.VERSION);

            about.getContent().setAll(launcher);
        }

        ComponentList thanks = new ComponentList();
        {
            IconedTwoLineListItem coldshine = new IconedTwoLineListItem();
            coldshine.setImage(FXUtils.newBuiltinImage("/assets/img/coldshine.png", 64, 64, false, true));
            coldshine.setTitle("Yukinoshita Coldshine");
            coldshine.setSubtitle(i18n("about.thanks_to.coldshine.statement"));
            coldshine.setExternalLink("https://land.coldshine.xyz/");

            thanks.getContent().setAll(coldshine);
        }

        ComponentList deps = new ComponentList();
        {
            IconedTwoLineListItem javafx = new IconedTwoLineListItem();
            javafx.setTitle("JavaFX");
            javafx.setSubtitle("Copyright © 2013, 2024, Oracle and/or its affiliates.\nLicensed under the GPL 2 with Classpath Exception.");
            javafx.setExternalLink("https://openjfx.io/");

            IconedTwoLineListItem jfoenix = new IconedTwoLineListItem();
            jfoenix.setTitle("JFoenix");
            jfoenix.setSubtitle("Copyright © 2016 JFoenix.\nLicensed under the MIT License.");
            jfoenix.setExternalLink("https://github.com/sshahine/JFoenix");

            IconedTwoLineListItem gson = new IconedTwoLineListItem();
            gson.setTitle("Gson");
            gson.setSubtitle("Copyright © 2008 Google Inc.\nLicensed under the Apache 2.0 License.");
            gson.setExternalLink("https://github.com/google/gson");

            IconedTwoLineListItem xz = new IconedTwoLineListItem();
            xz.setTitle("XZ for Java");
            xz.setSubtitle("Lasse Collin, Igor Pavlov, and/or Brett Okken.\nPublic Domain.");
            xz.setExternalLink("https://tukaani.org/xz/java.html");

            IconedTwoLineListItem fxgson = new IconedTwoLineListItem();
            fxgson.setTitle("fx-gson");
            fxgson.setSubtitle("Copyright © 2016 Joffrey Bion.\nLicensed under the MIT License.");
            fxgson.setExternalLink("https://github.com/joffrey-bion/fx-gson");

            IconedTwoLineListItem constantPoolScanner = new IconedTwoLineListItem();
            constantPoolScanner.setTitle("Constant Pool Scanner");
            constantPoolScanner.setSubtitle("Copyright © 1997-2010 Oracle and/or its affiliates.\nLicensed under the GPL 2 or the CDDL.");
            constantPoolScanner.setExternalLink("https://github.com/jenkinsci/constant-pool-scanner");

            IconedTwoLineListItem openNBT = new IconedTwoLineListItem();
            openNBT.setTitle("OpenNBT");
            openNBT.setSubtitle("Copyright © 2013-2021 Steveice10.\nLicensed under the MIT License.");
            openNBT.setExternalLink("https://github.com/GeyserMC/OpenNBT");

            IconedTwoLineListItem minecraftJFXSkin = new IconedTwoLineListItem();
            minecraftJFXSkin.setTitle("minecraft-jfx-skin");
            minecraftJFXSkin.setSubtitle("Copyright © 2016 InfinityStudio.\nLicensed under the GPL 3.");
            minecraftJFXSkin.setExternalLink("https://github.com/InfinityStudio/minecraft-jfx-skin");

            deps.getContent().setAll(javafx, jfoenix, gson, xz, fxgson, constantPoolScanner, openNBT, minecraftJFXSkin);
        }

        ComponentList legal = new ComponentList();
        {
            IconedTwoLineListItem copyright = new IconedTwoLineListItem();
            copyright.setTitle(i18n("about.copyright"));
            copyright.setSubtitle(i18n("about.copyright.statement"));
            copyright.setExternalLink("https://hmcl.huangyuhui.net/about/");

            legal.getContent().setAll(copyright);
        }

        VBox content = new VBox(16);
        content.setPadding(new Insets(10));
        content.getChildren().setAll(
                ComponentList.createComponentListTitle(i18n("about")),
                about,

                ComponentList.createComponentListTitle(i18n("about.thanks_to")),
                thanks,

                ComponentList.createComponentListTitle(i18n("about.dependency")),
                deps,

                ComponentList.createComponentListTitle(i18n("about.legal")),
                legal
        );


        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        FXUtils.smoothScrolling(scrollPane);
        getChildren().setAll(scrollPane);
    }
}
