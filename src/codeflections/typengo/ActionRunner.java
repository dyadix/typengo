package codeflections.typengo;

import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author dyadix
 */
public interface ActionRunner {
    String TYPE_N_GO_PLACE = "TypeNGo Plugin";

    void runAction(@NotNull Component sourceComponent,
                   @NotNull AnActionEvent originalEvent);
}
