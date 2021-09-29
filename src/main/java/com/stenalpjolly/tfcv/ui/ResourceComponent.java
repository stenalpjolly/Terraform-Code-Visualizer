package com.stenalpjolly.tfcv.ui;

import java.awt.Color;
import java.util.Map;
import javax.swing.JButton;

public class ResourceComponent extends Component{

  public ResourceComponent(Map<String, Object> value) {
    for (String m : value.keySet()) {
      Map<String, Object> subValue = (Map<String, Object>) value.get(m);
      String text = m + ", " + subValue.size();
      JButton button = new JButton(text);
      button.setBackground(new Color(59, 182, 88));
      button.setBorderPainted(false);
      add(button);
    }
  }

}
