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

package net.dyadix.typengo;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Contains all essential action information.
 *
 * @author dyadix
 */
public class ActionInfo {
    private final @NotNull String abbreviation;
    private final @NotNull String actionId;

    public ActionInfo(@NotNull String abbreviation, @NotNull String actionId) {
        this.abbreviation = abbreviation;
        this.actionId = actionId;
    }

    @Nullable
    public AnAction getAction() {
        return ActionManager.getInstance().getAction(actionId);
    }

    @NotNull
    public String getAbbreviation() {
        return abbreviation;
    }

}
