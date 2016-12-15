/*
 * Copyright (c) R.Vishniakov <dev@codeflections.com>, 2015-2016
 *
 * This file is part of TypeNGo Plugin.
 *
 * TypeNGo Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TypeNGo Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.codeflections.typengo;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.actionSystem.Shortcut;
import com.intellij.openapi.editor.colors.EditorColors;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.keymap.KeymapUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.wm.IdeFrame;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.openapi.wm.ex.WindowManagerEx;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.GuiUtils;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;

/**
 * @author dyadix
 */
public class CommandInputForm extends JDialog {

    public static final int ABBR_FIELD_SIZE = 5;

    private JPanel topPanel;
    private JTextField commandField;
    private final Component sourceComponent;
    private AnActionEvent originalEvent;
    private String currTyped;
    private final JPopupMenu popupMenu;
    private final JFrame ideFrame;

    private static CommandInputForm currInstance;

    private CommandInputForm(final JFrame ideFrame, Component sourceComponent, AnActionEvent originalEvent) {
        super(ideFrame, true);
        this.ideFrame = ideFrame;
        this.setUndecorated(true);
        this.sourceComponent = sourceComponent;
        this.originalEvent = originalEvent;
        this.add(topPanel);
        EditorColorsScheme scheme = EditorColorsManager.getInstance().getGlobalScheme();
        Font commandFont = new Font(scheme.getConsoleFontName(), Font.PLAIN, scheme.getConsoleFontSize());
        commandField.setFont(commandFont);
        this.pack();
        popupMenu = new JPopupMenu();
        topPanel.setComponentPopupMenu(popupMenu);
        topPanel.setBorder(BorderFactory.createLineBorder(JBColor.gray));
        this.setAlwaysOnTop(true);
        commandField.setRequestFocusEnabled(true);
        commandField.addActionListener(e -> {

        });
        KeyStroke escKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
        commandField.registerKeyboardAction(e -> {
            popupMenu.setVisible(false);
            CommandInputForm.this.setVisible(false);
            CommandInputForm.this.dispose();
            currTyped = null;
            focusOnIdeFrame(ideFrame);
        }, escKeyStroke, JComponent.WHEN_FOCUSED);
        commandField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                AnAction action = null;
                boolean isCommandTyped = false;
                if (Character.isLetter(c) || c == '-' || c == '+' || c == '{' || c == '}') {
                    currTyped = commandField.getText();
                    if (currTyped != null) {
                        currTyped += c;
                        ActionInfo actionInfo = ActionFinder.findAction(getActionId(currTyped));
                        action = actionInfo != null ? actionInfo.getAction() : null;
                    }
                    isCommandTyped = true;
                } else {
                    popupMenu.setVisible(false);
                }
                if (action != null) {
                    if (!(currTyped.endsWith("+") || currTyped.endsWith("-"))) {
                        popupMenu.setVisible(false);
                        CommandInputForm.this.setVisible(false);
                        CommandInputForm.this.dispose();
                        currTyped = null;
                    }
                    invokeAction(action);
                } else {
                    if (isCommandTyped && currTyped != null) {
                        popupMenu.setVisible(false);
                        updatePopup(popupMenu, currTyped);
                        Point location = commandField.getLocationOnScreen();
                        location = new Point(location.x, location.y + commandField.getHeight());
                        popupMenu.setLocation(location);
                        popupMenu.setVisible(true);
                    } else {
                        popupMenu.setVisible(false);
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void invokeAction(final AnAction action) {
        if (action != null && sourceComponent != null) {
            focusOnIdeFrame(ideFrame);
            ActionRunnerFactory.createActionRunner(action).runAction(sourceComponent, originalEvent);
        }
    }

    private void focusOnIdeFrame(@Nullable JFrame ideFrame) {
        if (ideFrame != null) {
            if (SystemInfo.isLinux) {
                if (!ideFrame.isFocused()) {
                    ideFrame.toFront();
                    ideFrame.requestFocus();
                }
            } else {
                ideFrame.requestFocus();
            }
            if (sourceComponent != null) {
                sourceComponent.requestFocusInWindow();
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // Ignore
            }
        }
    }

    @NotNull
    private String getActionId(@NotNull String typed) {
        if (typed.length() > 0) {
            char lastChar = typed.charAt(typed.length() - 1);
            switch (lastChar) {
                case '+':
                    return stripRepeatingTrailingChar(typed, '+');
                case '-':
                    return stripRepeatingTrailingChar(typed, '-');
            }
        }
        return typed;
    }

    @NotNull
    private String stripRepeatingTrailingChar(@NotNull String typed, char c) {
        int index = typed.length() - 1;
        while (index >= 0 && typed.charAt(index) == c) index --;
        index ++;
        return typed.substring(0, index + 1);
    }

    static void show(Component sourceComponent, AnActionEvent originalEvent) {
        if (currInstance != null) {
            currInstance.setVisible(false);
            currInstance.dispose();
        }
        currInstance = new CommandInputForm(getIdeFrame(originalEvent.getProject()), sourceComponent, originalEvent);
        currInstance.centerOnIdeFrameOrScreen(originalEvent);
        currInstance.setVisible(true);
    }

    static boolean isShown() {
        return currInstance != null && currInstance.isVisible();
    }
    
    private static JFrame getIdeFrame(@Nullable Project project) {
        return project != null ? WindowManager.getInstance().getFrame(project) : null;
    }

    private void updatePopup(@NotNull JPopupMenu popupMenu, @NotNull String typedStr) {
        popupMenu.removeAll();
        Collection<ActionInfo> foundActions = ActionFinder.findActions(typedStr);
        EditorColorsScheme currScheme = EditorColorsManager.getInstance().getGlobalScheme();
        Color backgroundColor = currScheme.getColor(EditorColors.NOTIFICATION_BACKGROUND);
        Color foregroundColor = currScheme.getDefaultForeground();
        if (backgroundColor == null) backgroundColor = currScheme.getDefaultBackground();
        Color shortcutColor = getShortcutColor(backgroundColor, foregroundColor);
        String shortcutStyle = "color:#" + GuiUtils.colorToHex(shortcutColor);
        String abbrStyle = "font-family:" + currScheme.getConsoleFontName() + ";font-style:oblique;";
        for (ActionInfo actionInfo: foundActions) {
            AnAction action = actionInfo.getAction();
            if (action != null) {
                Presentation presentation = actionInfo.getAction().getTemplatePresentation();
                StringBuilder sb = new StringBuilder();
                sb
                        .append("<html><span style='").append(abbrStyle).append("'>")
                        .append(fillString(actionInfo.getAbbreviation(), ABBR_FIELD_SIZE))
                        .append("</span>");
                String desc = presentation.getDescription();
                if (desc != null && !desc.isEmpty()) {
                    sb.append(desc);
                } else {
                    String text = presentation.getText();
                    if (text != null && !text.isEmpty()) {
                        sb.append(text);
                    }
                }
                Shortcut[] shortcuts = action.getShortcutSet().getShortcuts();
                if (shortcuts.length > 0) {
                    sb.append("&nbsp;&nbsp;<i style='").append(shortcutStyle).append("'>");
                    for (Shortcut shortcut : action.getShortcutSet().getShortcuts()) {
                        if (shortcut != shortcuts[0]) {
                            sb.append(", ");
                        }
                        sb.append(KeymapUtil.getShortcutText(shortcut));
                    }
                    sb.append("</i>");
                }
                sb.append("</html>");
                JMenuItem menuItem = new JMenuItem(sb.toString());
                menuItem.setForeground(foregroundColor);
                menuItem.setBackground(backgroundColor);
                popupMenu.add(menuItem);
            }
        }
    }

    private String fillString(@NotNull String str, int size) {
        return str.length() < size ? str + StringUtil.repeat("&nbsp;", size - str.length()) : str;
    }

    @SuppressWarnings("UseJBColor")
    private Color getShortcutColor(@NotNull Color background, @NotNull Color foreground) {
        return ColorUtil.mix(background, foreground, 0.5);
    }

    private void centerOnIdeFrameOrScreen(@NotNull AnActionEvent actionEvent) {
        WindowManagerEx windowManager = WindowManagerEx.getInstanceEx();
        IdeFrame frame = windowManager.getFrame(actionEvent.getProject());
        int x = 0;
        int y = 0;
        if (frame != null) {
            Component frameComponent = frame.getComponent();
            if (frameComponent != null) {
                Point origin = frameComponent.getLocationOnScreen();
                x = (int)(origin.getX() + (frameComponent.getWidth() - this.getWidth()) / 2);
                y = (int)(origin.getY() + (frameComponent.getHeight() - this.getHeight()) / 2);
            }
        }
        else {
            Rectangle screenBounds = windowManager.getScreenBounds();
            x = (int)(screenBounds.getX()  + (screenBounds.getWidth() - this.getWidth()) / 2);
            y = (int)(screenBounds.getY() + (screenBounds.getHeight() - this.getHeight()) / 2);
        }
        this.setLocation(x, y);
    }
}
