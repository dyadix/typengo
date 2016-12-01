package codeflections.typengo;

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
    private @NotNull String actionId;

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

    @NotNull
    public String getActionId() {
        return actionId;
    }
}
