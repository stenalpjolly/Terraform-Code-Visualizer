package com.stenalpjolly.tfcv.editor;


import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorLocation;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.VirtualFile;
import com.stenalpjolly.tfcv.data.Aggregator;
import java.beans.PropertyChangeListener;
import javax.swing.JComponent;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nls.Capitalization;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TerraformVisualizerFileEditor implements FileEditor {

  private JComponent component;
  private final VirtualFile virtualFile;

  public TerraformVisualizerFileEditor(Project project, VirtualFile file) {
    this.virtualFile = file;
    Document document = FileDocumentManager.getInstance().getDocument(file);
    if (document != null) {
      Aggregator aggregator = new Aggregator(document.getText());
      this.component = aggregator.getRenderedComponent();
    }
  }

  @Override
  public @NotNull JComponent getComponent() {
    return component;
  }

  @Override
  public @Nullable JComponent getPreferredFocusedComponent() {
    return component;
  }

  @Override
  public @Nls(capitalization = Capitalization.Title) @NotNull String getName() {
    return "Diagram";
  }

  @Override
  public void setState(@NotNull FileEditorState state) {

  }

  @Override
  public boolean isModified() {
    return false;
  }

  @Override
  public boolean isValid() {
    return true;
  }

  @Override
  public void addPropertyChangeListener(@NotNull PropertyChangeListener listener) {

  }

  @Override
  public void removePropertyChangeListener(@NotNull PropertyChangeListener listener) {

  }

  @Override
  public @Nullable FileEditorLocation getCurrentLocation() {
    return null;
  }

  @Override
  public @Nullable VirtualFile getFile() {
    return virtualFile;
  }

  @Override
  public void dispose() {
  }

  @Override
  public <T> @Nullable T getUserData(@NotNull Key<T> key) {
    return null;
  }

  @Override
  public <T> void putUserData(@NotNull Key<T> key, @Nullable T value) {

  }
}
