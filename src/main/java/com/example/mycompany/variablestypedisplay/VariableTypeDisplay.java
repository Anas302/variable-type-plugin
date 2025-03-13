package com.example.mycompany.variablestypedisplay;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import org.jetbrains.annotations.NotNull;

public class VariableTypeDisplay extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        // Get the current project
        Project project = e.getProject();
        if (project == null) {
            System.out.println("Project is null");
            return;
        }
        StatusBar statusBar = WindowManager.getInstance().getStatusBar(project);
        System.out.println("Setting Status Bar to Hello world");
        statusBar.setInfo("Hello World");
    }
}