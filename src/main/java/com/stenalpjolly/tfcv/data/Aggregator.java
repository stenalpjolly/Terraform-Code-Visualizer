package com.stenalpjolly.tfcv.data;

import com.bertramlabs.plugins.hcl4j.HCLParserException;
import com.stenalpjolly.tfcv.ui.Visualizer;
import java.io.IOException;
import java.util.Map;
import javax.swing.JComponent;
import com.bertramlabs.plugins.hcl4j.HCLParser;

public class Aggregator {

  private Visualizer renderedUI;

  public Aggregator(String content){
    try {
      Map<String, Object> parsedTfCode = new HCLParser().parse(content, true);
      renderedUI = new Visualizer(parsedTfCode);
    } catch (HCLParserException | IOException e) {
      renderedUI = new Visualizer();
      e.printStackTrace();
    }
  }

  public JComponent getRenderedComponent() {
    return renderedUI;
  }
}
