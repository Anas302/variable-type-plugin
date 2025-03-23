package com.example.mycompany.variablestypedisplay;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.openapi.editor.event.CaretListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtilBase;
import com.jetbrains.python.psi.*;
import com.jetbrains.python.psi.types.PyType;
import com.jetbrains.python.psi.types.TypeEvalContext;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class VariableTypeCaretListener implements CaretListener {
    @Override
    public void caretPositionChanged(@NotNull CaretEvent event) {
        Editor editor = event.getEditor();
        Project project = editor.getProject();
        if (project == null) return;
        PsiFile psiFile = PsiUtilBase.getPsiFileInEditor(editor, project);
        if (psiFile == null) return;

        int offset = Objects.requireNonNull(event.getCaret()).getOffset();
        PsiElement element = psiFile.findElementAt(offset);
        if (element == null) return;

        // find the type of the variable at the caret
        PyExpression expression = PsiTreeUtil.getParentOfType(element, PyExpression.class);
        if (expression != null) {
            PsiReference reference = expression.getReference();
            if (reference != null) {
                PsiElement resolvedElement = reference.resolve();
                if (resolvedElement instanceof PyTypedElement)
                    resolveAndSetType((PyTypedElement) resolvedElement, project, psiFile);
            }
        }
    }

    private void resolveAndSetType(PyTypedElement element, Project project, PsiFile psiFile) {
        TypeEvalContext evalContext = TypeEvalContext.userInitiated(project, psiFile);
        PyType type = evalContext.getType(element);

        // Update the status bar with the type
        if (type != null) {
            StatusBar statusBar = WindowManager.getInstance().getStatusBar(project);
            statusBar.setInfo("Variable " + element.getName() + ": " + type.getName());
        }
    }
}

