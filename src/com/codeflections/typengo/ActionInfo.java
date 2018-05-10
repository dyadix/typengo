/*
 * Copyright (c) R.Vishniakov <dev@codeflections.com>
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

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Contains all essential action information.
 *
 * @author dyadix
 */
public class ActionInfo {
    public static final String POPUP_SUFFIX = "!";
    private final @NotNull String abbreviation;
    private @NotNull String actionId;
    private boolean hasPopup;

    public ActionInfo(@NotNull String abbreviation, @NotNull String actionId) {
        this(abbreviation, actionId, false);
    }

    public ActionInfo(@NotNull String abbreviation, @NotNull String actionId, boolean hasPopup) {
        Pair<String,Boolean> abbrData = parseAbbreviation(abbreviation);
        this.abbreviation = abbrData.first;
        this.actionId = actionId;
        this.hasPopup = hasPopup || abbrData.second;
    }

    private Pair<String, Boolean> parseAbbreviation(@NotNull String abbreviation) {
        if (abbreviation.endsWith(POPUP_SUFFIX)) {
            return Pair.create(abbreviation.substring(0, abbreviation.length() - POPUP_SUFFIX.length()), true);
        }
        return Pair.create(abbreviation, false);
    }

    @Nullable
    public AnAction getAction() {
        return ActionManager.getInstance().getAction(actionId);
    }

    @NotNull
    public String getAbbreviation() {
        return abbreviation;
    }

    public boolean hasPopup() {
        return this.hasPopup;
    }
}
