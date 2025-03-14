// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.example.mycompany.variablestypedisplay;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.ProjectActivity;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class ProjectStartupActivity implements ProjectActivity {
    @Override
    public @Nullable Object execute(@NotNull Project project, @NotNull Continuation<? super Unit> continuation) {
        var projectService = project.getService(VariableTypeDisplayService.class);
        StatusBar statusBar = WindowManager.getInstance().getStatusBar(project);
        if (statusBar != null /*&& project != null*/) {
            VariableTypeWidget widget = new VariableTypeWidget();
            statusBar.addWidget(widget, "before Position"); // Specify a position for the widget
            System.out.println("StatusBus Set to Hello World");
        }
        return null;
    }
}

