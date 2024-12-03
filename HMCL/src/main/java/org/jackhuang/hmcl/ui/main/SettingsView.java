/*
 * Hello Minecraft! Launcher
 * Copyright (C) 2020  huangyuhui <huanghongxun2008@126.com> and contributors
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

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import org.jackhuang.hmcl.Metadata;
import org.jackhuang.hmcl.setting.EnumCommonDirectory;
import org.jackhuang.hmcl.setting.Theme;
import org.jackhuang.hmcl.ui.FXUtils;
import org.jackhuang.hmcl.ui.SVG;
import org.jackhuang.hmcl.ui.construct.ComponentList;
import org.jackhuang.hmcl.ui.construct.ComponentSublist;
import org.jackhuang.hmcl.ui.construct.MultiFileItem;
import org.jackhuang.hmcl.util.i18n.I18n;
import org.jackhuang.hmcl.util.i18n.Locales.SupportedLocale;

import java.util.Arrays;

import static org.jackhuang.hmcl.setting.ConfigHolder.config;
import static org.jackhuang.hmcl.ui.FXUtils.stringConverter;
import static org.jackhuang.hmcl.util.i18n.I18n.i18n;

public abstract class SettingsView extends StackPane {
    protected final JFXComboBox<SupportedLocale> cboLanguage;
    protected final MultiFileItem<EnumCommonDirectory> fileCommonLocation;
    protected final ComponentSublist fileCommonLocationSublist;

    protected final ScrollPane scroll;

    public SettingsView() {
        scroll = new ScrollPane();
        getChildren().setAll(scroll);
        scroll.setStyle("-fx-font-size: 14;");
        scroll.setFitToWidth(true);

        {
            VBox rootPane = new VBox();
            rootPane.setPadding(new Insets(10, 10, 32, 10));
            {
                ComponentList settingsPane = new ComponentList();
                {

                }

                {
                    fileCommonLocation = new MultiFileItem<>();
                    fileCommonLocationSublist = new ComponentSublist();
                    fileCommonLocationSublist.getContent().add(fileCommonLocation);
                    fileCommonLocationSublist.setTitle(i18n("launcher.cache_directory"));
                    fileCommonLocationSublist.setHasSubtitle(true);
                    fileCommonLocation.loadChildren(Arrays.asList(
                            new MultiFileItem.Option<>(i18n("launcher.cache_directory.default"), EnumCommonDirectory.DEFAULT),
                            new MultiFileItem.FileOption<>(i18n("settings.custom"), EnumCommonDirectory.CUSTOM)
                                    .setChooserTitle(i18n("launcher.cache_directory.choose"))
                                    .setDirectory(true)
                                    .bindBidirectional(config().commonDirectoryProperty())
                    ));

                    {
                        JFXButton cleanButton = new JFXButton(i18n("launcher.cache_directory.clean"));
                        cleanButton.setOnAction(e -> clearCacheDirectory());
                        cleanButton.getStyleClass().add("jfx-button-border");

                        fileCommonLocationSublist.setHeaderRight(cleanButton);
                    }

                    settingsPane.getContent().add(fileCommonLocationSublist);
                }

                {
                    BorderPane languagePane = new BorderPane();

                    Label left = new Label(i18n("settings.launcher.language"));
                    BorderPane.setAlignment(left, Pos.CENTER_LEFT);
                    languagePane.setLeft(left);

                    cboLanguage = new JFXComboBox<>();
                    cboLanguage.setConverter(stringConverter(I18n::getName));
                    FXUtils.setLimitWidth(cboLanguage, 300);
                    languagePane.setRight(cboLanguage);

                    settingsPane.getContent().add(languagePane);
                }

                {
                    BorderPane debugPane = new BorderPane();

                    Label left = new Label(i18n("settings.launcher.debug"));
                    BorderPane.setAlignment(left, Pos.CENTER_LEFT);
                    debugPane.setLeft(left);

                    JFXButton openLogFolderButton = new JFXButton(i18n("settings.launcher.launcher_log.reveal"));
                    openLogFolderButton.setOnAction(e -> openLogFolder());
                    openLogFolderButton.getStyleClass().add("jfx-button-border");

                    JFXButton logButton = new JFXButton(i18n("settings.launcher.launcher_log.export"));
                    logButton.setOnAction(e -> onExportLogs());
                    logButton.getStyleClass().add("jfx-button-border");

                    HBox buttonBox = new HBox();
                    buttonBox.setSpacing(10);
                    buttonBox.getChildren().addAll(openLogFolderButton, logButton);
                    BorderPane.setAlignment(buttonBox, Pos.CENTER_RIGHT);
                    debugPane.setRight(buttonBox);

                    settingsPane.getContent().add(debugPane);
                }

                rootPane.getChildren().add(settingsPane);
            }
            scroll.setContent(rootPane);
        }
    }

    public void openLogFolder() {
        FXUtils.openFolder(Metadata.HMCL_DIRECTORY.resolve("logs").toFile());
    }

    protected abstract void onUpdate();

    protected abstract void onExportLogs();

    protected abstract void onSponsor();

    protected abstract void clearCacheDirectory();
}
