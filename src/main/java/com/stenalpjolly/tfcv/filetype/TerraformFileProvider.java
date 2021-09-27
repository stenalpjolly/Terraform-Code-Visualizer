package com.stenalpjolly.tfcv.filetype;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.util.NlsContexts.Label;
import com.intellij.openapi.util.NlsSafe;
import javax.swing.Icon;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TerraformFileProvider implements FileType {

  @Override
  public @NonNls @NotNull String getName() {
    return "Terrform";
  }

  @Override
  public @Label @NotNull String getDescription() {
    return "Terraform";
  }

  @Override
  public @NlsSafe @NotNull String getDefaultExtension() {
    return "tf";
  }

  @Override
  public @Nullable Icon getIcon() {
    return null;
  }

  @Override
  public boolean isBinary() {
    return false;
  }
}
