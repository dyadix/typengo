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

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dyadix
 */
public class BuiltInActions {
    private final static Map<String,ActionInfo> BUILT_IN = new HashMap<>();
    private final static Map<String,ActionInfo> ACTIONS_BY_ID = new HashMap<>(); 

    static {
        //
        // General
        //
        define("ps", "ShowProjectStructureSettings");
        define("ss", "ShowSettings");
        define("rf", "RecentFiles");
        define("rp", "ManageRecentProjects");
        define("fs", "ToggleFullScreen");
        define("st", "FileStructurePopup");
        //
        // Navigation
        //
        define("gr", "GotoRelated");
        define("gb", "GotoSuperMethod");
        define("gt", "GotoTypeDeclaration");
        define("gc", "GotoClass");
        define("gd", "GotoDeclaration");
        define("gf", "GotoFile");
        define("gs", "GotoSymbol");
        define("gl", "GotoLine");
        define("gi", "GotoImplementation");
        define("g-", "Back");
        define("g+", "Forward");
        //
        // Editor
        //
        define("dd", "EditorDeleteLine");
        define("su", "SurroundWith");
        define("du", "EditorDuplicateLines");
        define("qnp", "CloseAllUnpinnedEditors");
        define("qnm", "CloseAllUnmodifiedEditors");
        define("qa", "CloseAllEditors");
        define("qo", "CloseAllEditorsButActive");
        define("qc", "CloseEditor");
        define("m{", "EditorCodeBlockStart");
        define("m}", "EditorCodeBlockEnd");
        define("s{", "EditorCodeBlockStartWithSelection");
        define("s}", "EditorCodeBlockEndWithSelection");
        define("s+", "EditorSelectWord");
        define("s-", "EditorUnSelectWord");
        define("fn+", "EditorIncreaseFontSize");
        define("fn-", "EditorDecreaseFontSize");
        define("ln", "EditorToggleShowLineNumbers");
        define("pi", "PinActiveTab");
        define("c-", "VcsShowPrevChangeMarker");
        define("c+","VcsShowNextChangeMarker");
        define("e-","GotoPreviousError");
        define("e+","GotoNextError");
        define("sl", "EditorSelectLine");
        define("ph", "ToggleInlineHintsAction");
        //
        // Tool Windows
        //
        define("te", "ActivateTerminalToolWindow");
        define("pr", "ActivateProjectToolWindow");
        define("vc", "ActivateChangesToolWindow");
        define("tt", "HideAllWindows");
        //
        // Find
        //
        define("fu", "FindUsages");
        define("fp", "FindInPath");
        define("fa", "GotoAction");
        define("se", "SearchEverywhere");
        //
        // Run/Debug
        //
        define("de", "DebugClass");
        define("dc", "ChooseDebugConfiguration");
        define("ds", "Debug");
        define("rs", "Run");
        define("ru", "RunClass");
        define("rc", "ChooseRunConfiguration");
        //
        // Version control
        //
        define("up", "Vcs.UpdateProject");
        define("hi", "Vcs.ShowTabbedFileHistory");
        define("cm", "ChangesView.Commit");
        define("pu", "Vcs.Push");
        define("cp", "Vcs.CherryPick");
        define("an", "Annotate");
        define("lh", "LocalHistory.ShowHistory");
        define("br", "Git.Branches");
        //
        // Format
        //
        define("fm", "ReformatCode");
        define("oi", "OptimizeImports");
        define("ai", "AutoIndentLines");
        //
        // Refactoring
        //
        define("xv", "IntroduceVariable");
        define("xm", "ExtractMethod");
        define("xc", "IntroduceConstant");
        define("xf", "IntroduceField");
        //
        // Compilation
        //
        define("mk", "CompileDirty");
        //
        // Other
        //
        define("cs", "ChangeCodeStyleScheme");
        define("cc", "ChangeColorScheme");
        define("km", "ChangeKeymap");
        define("lf", "ChangeLaf");
        define("vm", "ChangeView");
        define("qt", "CloseActiveTab");
        define("dt", "ShowDiscoveredTests");
    }

    public static void define(@NotNull String abbreviation, @NotNull String actionId) {
        final ActionInfo actionInfo = new ActionInfo(abbreviation, actionId);
        BUILT_IN.put(abbreviation, actionInfo);
        ACTIONS_BY_ID.put(actionId, actionInfo);
    }

    @Nullable
    public static ActionInfo getActionInfoById(@NotNull String actionId) {
        return ACTIONS_BY_ID.get(actionId);
    }

    @Nullable
    public static ActionInfo getActionInfo(@NotNull String abbreviation) {
        return BUILT_IN.get(abbreviation);
    }

    public static Collection<String> getAbbreviations() {
        return BUILT_IN.keySet();
    }

}
