package com.example.mycompany.variablestypedisplay;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.CustomStatusBarWidget;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.openapi.wm.impl.status.TextPanel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

class VariableTypeWidget implements CustomStatusBarWidget {
    public static final String ID = "variable_type_display";
    private final HelloWorldTextPanel myComponent = new HelloWorldTextPanel();

    @Override
    public @NotNull String ID() {
        return ID;
    }

    @Override
    public JComponent getComponent() {
        return myComponent;
    }

    @Override
    public void install(@NotNull StatusBar statusBar) {
        // Perform any setup required when the widget is installed
    }

    @Override
    public void dispose() {
        // Clean up resources when the widget is disposed
    }
}


public class VariableTypeDisplay extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        // Get the current project
        Project project = e.getProject();
        if (project == null)
            return;
        StatusBar statusBar = WindowManager.getInstance().getStatusBar(project);
        if (statusBar != null) {
            VariableTypeWidget variableTypeWidget = new VariableTypeWidget();
            statusBar.addWidget(variableTypeWidget, "before Position");
        }
    }
}

class HelloWorldTextPanel extends TextPanel {
    public HelloWorldTextPanel() {
        super();
        setText("Hello World");
    }
}