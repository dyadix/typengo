/*
 * Copyright (c) R.Vishniakov <dyadix@gmail.com>, 2015-2020
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
 * along with TypeNGo.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.codeflections.typengo;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.DumbAware;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author dyadix
 */
public class TypeAndGoInvokeAction extends AnAction implements DumbAware {

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        if (!CommandInputForm.isShown()) {
            Component component = anActionEvent.getData(PlatformDataKeys.CONTEXT_COMPONENT);
            if (component == null) {
                component = anActionEvent.getInputEvent().getComponent();
            }
            CommandInputForm.show(component, anActionEvent);
        }
    }
}
