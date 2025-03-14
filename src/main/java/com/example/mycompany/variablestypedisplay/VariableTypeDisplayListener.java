package com.example.mycompany.variablestypedisplay;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManagerListener;
import org.jetbrains.annotations.NotNull;

final class VariableTypeDisplayListener implements ProjectManagerListener {
    @Override
    public void projectClosed(@NotNull Project project) {
        VariableTypeDisplayService variableTypeDisplayService = project.getService(VariableTypeDisplayService.class);
        variableTypeDisplayService.dispose();
    }
}

