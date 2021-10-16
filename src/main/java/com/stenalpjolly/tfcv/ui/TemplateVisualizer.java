package com.stenalpjolly.tfcv.ui;

import com.bertramlabs.plugins.hcl4j.RuntimeSymbols.Variable;
import com.github.jknack.handlebars.EscapingStrategy;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.intellij.ui.jcef.JBCefBrowser;
import com.stenalpjolly.tfcv.data.EntityData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.swing.JComponent;

public class TemplateVisualizer {

  private final JBCefBrowser browser;
  private static final String[] resourceNames = new String[]{
      "resource",
      "variable",
      "module",
      "data",
      "output",
      "locals"
  };

  public TemplateVisualizer(Map<String, Object> parsedTfCode) throws IOException {
    Handlebars handlebars = new Handlebars().with(EscapingStrategy.NOOP);
    Template template = handlebars.compile("templates/main");
    EntityData entityData = new EntityData("tf");

    for (String resourceName : resourceNames) {
      EntityData data = extractEntity(parsedTfCode, resourceName, 1);
      entityData.add(data);
    }

    HashMap<String, EntityData> hashMap = new HashMap<>();

    if (entityData.getDataCount() < 1) {
      throw new IOException("No Supported entity found");
    } else if (entityData.getDataCount() == 1) {
      EntityData data = entityData.getChild();
      hashMap.put("data", data);
    } else {
      hashMap.put("data", entityData);
    }

    String generatedFromTemplate = template.apply(hashMap);

    File temp = File.createTempFile("temp1", ".html");
    FileWriter fileWriter = new FileWriter(temp);
    fileWriter.write(generatedFromTemplate);
    fileWriter.flush();

    this.browser = new JBCefBrowser("file://" + temp.getAbsolutePath());

  }

  private EntityData extractEntity(Map<String, Object> parsedTfCode, String entityResource, int depth) {
    if (parsedTfCode == null || depth > 3) {
      return null;
    }
    Object resourceObj = parsedTfCode.get(entityResource);
    if (resourceObj instanceof String) {
      return new EntityData(resourceObj.toString());
    } else if (resourceObj instanceof Variable) {
      return new EntityData(((Variable) resourceObj).getName());
    } else if (resourceObj instanceof Map) {
      Map<String, Object>  entityMap = (Map<String, Object>) resourceObj;
      EntityData data = new EntityData(entityResource);
      for (String resourceKey : entityMap.keySet()) {
        EntityData resourceData = extractEntity(entityMap, resourceKey, depth + 1);
        data.add(Objects.requireNonNullElseGet(resourceData, () -> new EntityData(resourceKey)));
      }
      return data;
    }
    return null;
  }

  public JComponent getRenderedComponent() {
    return browser.getComponent();
  }
}
