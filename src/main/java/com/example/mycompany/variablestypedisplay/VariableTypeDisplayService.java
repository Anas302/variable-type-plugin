package com.example.mycompany.variablestypedisplay;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import org.jetbrains.annotations.NotNull;


@Service(Service.Level.PROJECT)
public final class VariableTypeDisplayService {

    private final Project project;

    public VariableTypeDisplayService(@NotNull Project project) {
        this.project = project;
    }

    public void initialize() {
        StatusBar statusBar = WindowManager.getInstance().getStatusBar(project);
        if (statusBar != null /*&& project != null*/) {
            VariableTypeWidget widget = new VariableTypeWidget();
            statusBar.addWidget(widget, "before Position"); // Specify a position for the widget
            System.out.println("StatusBus Set to Hello World");
        }
    }

    public void dispose() {
        StatusBar statusBar = WindowManager.getInstance().getStatusBar(project);
        if (statusBar != null)
            statusBar.removeWidget(VariableTypeWidget.ID);
    }
}