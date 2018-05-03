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
 * TypeNGo is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.codeflections.typengo;

import com.intellij.openapi.actionSystem.AbbreviationManager;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * @author dyadix
 */
public class ActionFinder {


    @Nullable
    public static ActionInfo findAction(String abbreviation) {
        String actionId;
        List<String> abbrActionIds = AbbreviationManager.getInstance().findActions(abbreviation);
        if (abbrActionIds.size() == 1) {
            actionId = abbrActionIds.get(0);
            ActionInfo builtInInfo = BuiltInActions.getActionInfoById(actionId);
            return builtInInfo != null ? builtInInfo : new ActionInfo(abbreviation, actionId);
        }
        else if (abbrActionIds.size() == 0) {
            return BuiltInActions.getActionInfo(abbreviation);
        }
        return null;
    }


    public static Collection<ActionInfo> findActions(String typedStr) {
        Set<String> abbreviations = AbbreviationManager.getInstance().getAbbreviations();
        Map<String,ActionInfo> foundActions = new TreeMap<>();
        for (String abbr : abbreviations) {
            if (abbr.startsWith(typedStr)) {
                ActionInfo found = findAction(abbr);
                if (found != null && !foundActions.containsKey(abbr)) {
                    foundActions.put(abbr, found);
                }
            }
        }
        for (String abbr : BuiltInActions.getAbbreviations()) {
            if (abbr.startsWith(typedStr)) {
                ActionInfo found = findAction(abbr);
                if (found != null && !foundActions.containsKey(abbr)) {
                    foundActions.put(abbr, found);
                }
            }
        }
        return foundActions.values();
    }
}
