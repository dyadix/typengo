package codeflections.typengo;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dyadix
 */
public class BuiltInActions {
    private final static Map<String,ActionInfo> BUILT_IN = new HashMap<String,ActionInfo>();

    static {
        //
        // General
        //
        define("ps", "ShowProjectStructureSettings");
        define("ss", "ShowSettings");
        define("rf", "RecentFiles");
        define("rp", "ManageRecentProjects");
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
        //
        // Editor
        //
        define("dd", "EditorDeleteLine");
        define("su", "SurroundWith");
        define("du", "EditorDuplicateLines");
        define("clp", "CloseAllUnpinnedEditors");
        define("clu", "CloseAllUnmodifiedEditors");
        define("cla", "CloseAllEditors");
        define("clo", "CloseAllEditorsButActive");
        define("cle", "CloseEditor");
        define("m{", "EditorCodeBlockStart");
        define("m}", "EditorCodeBlockEnd");
        define("s{", "EditorCodeBlockStartWithSelection");
        define("s}", "EditorCodeBlockEndWithSelection");
        define("s+", "EditorSelectWord");
        define("s-", "EditorUnSelectWord");
        define("fn+", "EditorIncreaseFontSize");
        define("fn-", "EditorDecreaseFontSize");
        define("ln", "EditorToggleShowLineNumbers");
        //
        // Tool Windows
        //
        define("te", "ActivateTerminalToolWindow");
        define("pr", "ActivateProjectToolWindow");
        define("vc", "ActivateChangesToolWindow");
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
        define("clt", "CloseActiveTab");
    }
    
    public static void define(@NotNull String abbreviation, @NotNull String actionId) {
        BUILT_IN.put(abbreviation, new ActionInfo(abbreviation, actionId));
    }

    @Nullable
    public static ActionInfo getActionInfo(@NotNull String abbreviation) {
        return BUILT_IN.get(abbreviation);
    }

    public static Collection<String> getAbbreviations() {
        return BUILT_IN.keySet();
    }

}
