package com.example.mycompany.variablestypedisplay;

import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.CaretListener;
import com.intellij.openapi.editor.event.EditorEventMulticaster;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.ProjectActivity;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;


public class VariablesTypeProjectActivity implements ProjectActivity {
    @Override
    public Object execute(@NotNull Project project, @NotNull Continuation<? super kotlin.Unit> continuation) {
        EditorEventMulticaster multicaster = EditorFactory.getInstance().getEventMulticaster();

        // register caret listener
        CaretListener caretListener = new VariableTypeCaretListener();
        multicaster.addCaretListener(caretListener, project);

        return null;
    }
}

