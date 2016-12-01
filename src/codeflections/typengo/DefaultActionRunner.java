package codeflections.typengo;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author dyadix
 */
public class DefaultActionRunner implements ActionRunner {
    protected AnAction action;

    public DefaultActionRunner(@NotNull AnAction action) {
        this.action = action;
    }

    @Override
    public void runAction(@NotNull Component sourceComponent, @NotNull AnActionEvent originalEvent) {
        ActionManager.getInstance().tryToExecute(action, originalEvent.getInputEvent(), sourceComponent, TYPE_N_GO_PLACE, true);
    }
}
