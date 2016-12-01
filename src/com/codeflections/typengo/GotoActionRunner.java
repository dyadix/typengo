package com.codeflections.typengo;

import com.intellij.ide.actions.GotoActionAction;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author dyadix
 */
public class GotoActionRunner extends DefaultActionRunner {
    public GotoActionRunner(@NotNull AnAction action) {
        super(action);
    }

    @Override
    public void runAction(@NotNull Component sourceComponent, @NotNull AnActionEvent originalEvent) {
        GotoActionAction.performAction(action, sourceComponent, originalEvent);
    }
}
