package com.stenalpjolly.tfcv.editor;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorPolicy;
import com.intellij.openapi.fileEditor.FileEditorProvider;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.jcef.JBCefApp;
import com.stenalpjolly.tfcv.TerraformFile;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class VisualizerProvider implements FileEditorProvider, DumbAware {

  public static final String EDITOR_ID = "terraform-code-visualizer";

  @Override
  public boolean accept(@NotNull Project project, @NotNull VirtualFile virtualFile) {
    try {
      if(!JBCefApp.isSupported()){
        return false;
      }
    }
    catch(Exception e){
      return false;
    }
    return TerraformFile.isExtensionSupported(virtualFile.getExtension());
  }

  @Override
  public @NotNull FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile file) {
    TerraformVisualizerFileEditor terraformVisualizerFileEditor = new TerraformVisualizerFileEditor(
        project, file);
    return terraformVisualizerFileEditor;
  }

  @Override
  public @NotNull @NonNls String getEditorTypeId() {
    return EDITOR_ID;
  }

  @Override
  public @NotNull FileEditorPolicy getPolicy() {
    return FileEditorPolicy.PLACE_AFTER_DEFAULT_EDITOR;
  }
}
