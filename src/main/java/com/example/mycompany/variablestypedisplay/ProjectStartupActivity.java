package com.example.mycompany.variablestypedisplay;

import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.ProjectActivity;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;


public class ProjectStartupActivity implements ProjectActivity {
    @Override
    public Object execute(@NotNull Project project, @NotNull Continuation<? super kotlin.Unit> continuation) {
        EditorFactory.getInstance().getEventMulticaster().addEditorMouseMotionListener(
            new VariableTypeListener(), project
        );
        return null;
    }
}

