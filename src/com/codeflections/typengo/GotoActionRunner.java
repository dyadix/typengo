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

import com.intellij.ide.actions.GotoActionAction;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.wm.IdeFocusManager;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author dyadix
 */
public class GotoActionRunner extends DefaultActionRunner {
    public GotoActionRunner(@NotNull AnAction action) {
        super(action);
    }

    @Override
    public void runAction(@NotNull Component sourceComponent, @NotNull AnActionEvent originalEvent) {
        ApplicationManager.getApplication().invokeLater(
            () -> IdeFocusManager.getGlobalInstance().doWhenFocusSettlesDown(
                () -> GotoActionAction.performAction(action, sourceComponent, originalEvent)));
    }
}
