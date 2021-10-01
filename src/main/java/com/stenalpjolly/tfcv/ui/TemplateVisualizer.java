package com.stenalpjolly.tfcv.ui;

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
import javax.swing.JComponent;

public class TemplateVisualizer {
  private JBCefBrowser browser;

  public TemplateVisualizer(Map<String, Object> parsedTfCode) throws IOException {
    Handlebars handlebars = new Handlebars().with(EscapingStrategy.NOOP);
    Template template = handlebars.compile("templates/main");
    EntityData entityData = new EntityData("tf");

    extractEntity(parsedTfCode, entityData, "resource");
    extractEntity(parsedTfCode, entityData, "variable");
    extractEntity(parsedTfCode, entityData, "module");
    extractEntity(parsedTfCode, entityData, "locals");

    HashMap<String, EntityData> hashMap = new HashMap<>();

    if (entityData.getDataCount() == 1) {
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

  private void extractEntity(Map<String, Object> parsedTfCode, EntityData entityData,
      String entityResource) {
    Map<String, Object>  entityMap = (Map<String, Object>) parsedTfCode.get(entityResource);
    if (entityMap != null) {
      EntityData data = new EntityData(entityResource);
      for (String resourceKey : entityMap.keySet()) {
        EntityData resourceData = new EntityData(resourceKey);
        Map<String, Object> stringObjectMap = (Map<String, Object>) entityMap.get(resourceKey);
        if (stringObjectMap != null) {
          resourceData.setChildren(stringObjectMap.keySet());
          data.add(resourceData);
        }
      }
      entityData.add(data);
    }
  }

  public JComponent getRenderedComponent() {
    return browser.getComponent();
  }
}
