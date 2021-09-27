package com.stenalpjolly.tfcv;

public class TerraformFile {

  public static final String[] SUPPORTED_TF_EXTENSIONS = {
      "tf"
  };

  public static boolean isExtensionSupported(String extension){
    if (extension == null){
      return false;
    }
    for (String supportedExtension : SUPPORTED_TF_EXTENSIONS) {
      if ( extension.equalsIgnoreCase(supportedExtension)){
        return true;
      }
    }
    return false;
  }
}
