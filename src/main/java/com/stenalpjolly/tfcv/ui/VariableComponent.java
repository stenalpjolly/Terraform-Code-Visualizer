package com.stenalpjolly.tfcv.ui;

import java.awt.Color;
import java.util.Map;
import javax.swing.JButton;

public class VariableComponent extends Component{

  public VariableComponent(Map<String, Object> value) {
    for (String m : value.keySet()) {
      Map<String, Object> subValue = (Map<String, Object>) value.get(m);
      String text = m + ", " + subValue.size();
      JButton button = new JButton(text);
      button.setBackground(new Color(59, 153, 182));
      button.setBorderPainted(false);
      add(button);
    }
  }

}
