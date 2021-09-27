package com.stenalpjolly.tfcv.editor;


import com.bertramlabs.plugins.hcl4j.HCLParserException;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorLocation;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.VirtualFile;
import com.stenalpjolly.tfcv.ui.Aggregator;
import java.awt.Color;
import java.awt.Graphics;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import javax.swing.JComponent;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nls.Capitalization;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TerraformVisualizerFileEditor implements FileEditor, Disposable {

  private final JComponent component;
  private VirtualFile virtualFile;

  public TerraformVisualizerFileEditor(Project project, VirtualFile file) {
    virtualFile = file;

    Document document = FileDocumentManager.getInstance().getDocument(file);
    String lineSeparator = FileDocumentManager.getInstance().getLineSeparator(file, project);

    JComponent tempComponent = null;
    try {
      tempComponent = new Aggregator(document, lineSeparator);
    } catch (HCLParserException | IOException e) {
      e.printStackTrace();
    }
    component = tempComponent;
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
