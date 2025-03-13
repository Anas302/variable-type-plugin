//package com.example.mycompany.variablestypedisplay;
//import com.intellij.openapi.editor.Editor;
//import com.intellij.openapi.editor.event.EditorMouseMotionListener;
//import com.intellij.openapi.wm.StatusBar;
//import com.intellij.openapi.wm.WindowManager;
//import com.intellij.psi.PsiElement;
//import com.intellij.psi.PsiFile;
//import com.intellij.psi.util.PsiTreeUtil;
//import org.jetbrains.annotations.NotNull;
//
//public class CursorMotionDetector implements EditorMouseMotionListener {
//    @Override
//    public void mouseMoved(@NotNull com.intellij.openapi.editor.event.EditorMouseEvent event) {
//        Editor editor = event.getEditor();
//        // Get the PSI element at the mouse position
//        PsiFile psiFile = e.getDataContext().getData(com.intellij.openapi.actionSystem.CommonDataKeys.PSI_FILE);
//        if (psiFile == null) return;
//
//        int offset = event.getOffset();
//        PsiElement element = psiFile.findElementAt(offset);
//        if (element == null) return;
//
//        // Find the variable (PyTargetExpression) under the cursor
//        PyTargetExpression variable = PsiTreeUtil.getParentOfType(element, PyTargetExpression.class);
//        if (variable == null) return;
//
//        // Get the type of the variable
//        PyType type = TypeEvalContext.codeAnalysis(project, psiFile).getType((PyExpression) variable);
//        if (type == null) return;
//
//        // Update the status bar with the variable's type
//        StatusBar statusBar = WindowManager.getInstance().getStatusBar(project);
//        if (statusBar != null) {
//            statusBar.setInfo("Variable type: " + type.getName());
//        }
//    }
//}
