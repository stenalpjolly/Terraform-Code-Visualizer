package com.stenalpjolly.tfcv.ui;

import java.awt.BorderLayout;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.NotNull;

public class Visualizer extends JPanel {

  // private HashMap<String, Component> entityMap =

  public Visualizer() {
    renderEmptyComponent();
  }

  private void renderEmptyComponent() {
    setLayout(new MigLayout());
    JLabel comp = new JLabel("There is no supported entity available to display.");
    add(comp, "push, align center");
  }

  public Visualizer(@NotNull Map<String, Object> dataMap) {
    setLayout(new BorderLayout());
    boolean hasContent = paintMap(dataMap);
    if (!hasContent) {
      renderEmptyComponent();
    }
  }

  private boolean paintMap(Map<String, Object> map ) {
    if (map == null) return false;

    boolean hasContent = false;

    for (String key : map.keySet()) {
      if (map.get(key) instanceof String) {
        break;
      }
      Map<String, Object> value = (Map<String, Object>) map.get(key);
      switch (key) {
        case "resource":
          add(new ResourceComponent(value));
          hasContent = true;
          break;
        case "variable":
          add(new VariableComponent(value));
          hasContent = true;
          break;
        default:
          hasContent = paintMap(value) || hasContent;
      }
    }
    return hasContent;
  }
}
