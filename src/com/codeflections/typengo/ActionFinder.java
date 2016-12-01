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
            return new ActionInfo(abbreviation, actionId);
        }
        else if (abbrActionIds.size() == 0) {
            return BuiltInActions.getActionInfo(abbreviation);
        }
        return null;
    }


    public static Collection<ActionInfo> findActions(String typedStr) {
        Set<String> abbreviations = AbbreviationManager.getInstance().getAbbreviations();
        Map<String,ActionInfo> foundActions = new TreeMap<String,ActionInfo>();
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
