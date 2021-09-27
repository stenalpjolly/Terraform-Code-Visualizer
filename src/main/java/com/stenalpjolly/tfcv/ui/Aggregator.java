package com.stenalpjolly.tfcv.ui;

import com.bertramlabs.plugins.hcl4j.HCLParserException;
import com.intellij.openapi.editor.Document;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import javax.swing.JComponent;
import com.bertramlabs.plugins.hcl4j.HCLParser;

public class Aggregator extends JComponent {

  private Map parsedTfCode;
  private int yLocation = 0;

  public Aggregator(Document document, String lineSeparator)
      throws HCLParserException, IOException {
    String content = document.getText();
    if (content.length() > 0) {
      parsedTfCode = new HCLParser().parse(content);
    }

  }

  @Override
  public void paint(Graphics g) {
    paintMap(parsedTfCode, g);
  }

  // Define paintMap method
  private void paintMap(Map map, Graphics g) {
    yLocation = 20;
    // Iterate over the keys in the map. Draw a button with text as key if the key is either a "resource" or "variable"
    for (Object key : map.keySet()) {
      if (Objects.equals(key, "resource") || Objects.equals(key, "variable")) {
        Map objectValueMap = (Map) map.get(key);
        for (Object m : objectValueMap.keySet()) {
          String text = m.toString() + ", " + ((Map)objectValueMap.get(m)).size();
          yLocation = yLocation + 30;
          g.drawRoundRect(10, yLocation - 15, text.length() * 8, 20, 5, 5);
          g.drawString(text, 15, yLocation);
        }
      } else {
        paintMap((Map) map.get(key), g);
      }
    }
    
  }
}
