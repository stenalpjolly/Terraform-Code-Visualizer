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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.text.html.parser.Entity;

public class TemplateVisualizer {
  private JBCefBrowser browser;

  public TemplateVisualizer(Map<String, Object> parsedTfCode) throws IOException {
    Handlebars handlebars = new Handlebars().with(EscapingStrategy.NOOP);
    Template template = handlebars.compile("templates/main");
    Map<String, Object>  resource = (Map<String, Object>) parsedTfCode.get("resource");

    // HashMap<String, Set<String>> resultMap = new HashMap<>();
    // // resultMap.put("name", )

    EntityData entityData = new EntityData("File Content");

    if (resource != null) {
      EntityData data = new EntityData("resources");
      data.setChildren(resource.keySet());
      entityData.add(data);
    }

    HashMap<String, EntityData> hashMap = new HashMap<>();
    hashMap.put("data", entityData);

    String generatedFromTemplate = template.apply(hashMap);

    File temp = File.createTempFile("temp1", ".html");
    FileWriter fileWriter = new FileWriter(temp);
    fileWriter.write(generatedFromTemplate);
    fileWriter.flush();

    this.browser = new JBCefBrowser("file://" + temp.getAbsolutePath());

  }

  public JComponent getRenderedComponent() {
    return browser.getComponent();
  }
}
