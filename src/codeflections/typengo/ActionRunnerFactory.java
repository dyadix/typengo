package codeflections.typengo;

import com.intellij.openapi.actionSystem.AnAction;
import org.jetbrains.annotations.NotNull;

/**
 * @author dyadix
 */
public class ActionRunnerFactory {

    @NotNull
    public static ActionRunner createActionRunner(@NotNull AnAction action) {
        return new GotoActionRunner(action);
    }
}