package com.example.mycompany.variablestypedisplay;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.EditorMouseEvent;
import com.intellij.openapi.editor.event.EditorMouseMotionListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtilBase;
import com.jetbrains.python.psi.PyReferenceExpression;
import com.jetbrains.python.psi.PyTargetExpression;
import com.jetbrains.python.psi.PyTypedElement;
import com.jetbrains.python.psi.types.PyType;
import com.jetbrains.python.psi.types.TypeEvalContext;
import org.jetbrains.annotations.NotNull;

public class VariableTypeListener implements EditorMouseMotionListener {
    @Override
    public void mouseMoved(@NotNull EditorMouseEvent event) {
        Editor editor = event.getEditor();
        Project project = editor.getProject();
        if (project == null) {
            System.out.println("Project is null");
            return;
        }

        PsiFile psiFile = PsiUtilBase.getPsiFileInEditor(editor, project);
        if (psiFile == null) {
            System.out.println("PsiFile is null");
            return;
        }

        int offset = editor.getCaretModel().getOffset();
        PsiElement element = psiFile.findElementAt(offset);
        if (element == null) {
            System.out.println("Element is null");
            return;
        }

        System.out.println("Element under cursor: " + element.getText());
        StatusBar statusBar = WindowManager.getInstance().getStatusBar(project);

        // Check if it's a variable declaration (x = 10)
        PyTargetExpression targetExpression = PsiTreeUtil.getParentOfType(element, PyTargetExpression.class);
        if (targetExpression != null) {
            System.out.println("Variable Declaration Found: " + targetExpression.getText());
            PyType type = resolveType(targetExpression, project, psiFile);
            statusBar.setInfo("Variable " + targetExpression.getName() + ": " + type.getName());
            return;
        }

        // Check if it's a variable reference (y = x)
        PyReferenceExpression referenceExpression = PsiTreeUtil.getParentOfType(element, PyReferenceExpression.class);
        if (referenceExpression != null) {
            System.out.println("Variable Reference Found: " + referenceExpression.getText());
            PsiReference ref = referenceExpression.getReference();
            if (ref != null) {
                PsiElement resolvedElement = ref.resolve();
                if (resolvedElement instanceof PyTypedElement){
                    PyType type = resolveType((PyTypedElement) resolvedElement, project, psiFile);
                    statusBar.setInfo("Variable " + ((PyTypedElement) resolvedElement).getName() + ": " + type.getName());
                }
            }
            return;
        }
        System.out.println("No valid Python variable found.");
    }

    private PyType resolveType(PyTypedElement element, Project project, PsiFile psiFile) {
        TypeEvalContext evalContext = TypeEvalContext.userInitiated(project, psiFile);
        PyType type = evalContext.getType(element);

        if (type != null) {
            System.out.println("Resolved Type: " + type.getName());
            return type;
        }
        System.out.println("Resolved Type: null (Type resolution failed)");
        return null;
    }
}
