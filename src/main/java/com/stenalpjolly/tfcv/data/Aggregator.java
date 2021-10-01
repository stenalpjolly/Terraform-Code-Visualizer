package com.stenalpjolly.tfcv.data;

import com.bertramlabs.plugins.hcl4j.HCLParser;
import com.bertramlabs.plugins.hcl4j.HCLParserException;
import com.stenalpjolly.tfcv.ui.TemplateVisualizer;
import com.stenalpjolly.tfcv.ui.Visualizer;
import java.io.IOException;
import java.util.Map;
import javax.swing.JComponent;

public class Aggregator {

  private final JComponent ui;

  public Aggregator(String content){
    JComponent renderedUI;
    try {
      Map<String, Object> parsedTfCode = new HCLParser().parse(content, true);
      renderedUI = new TemplateVisualizer(parsedTfCode).getRenderedComponent();
    } catch (HCLParserException | IOException e) {
      renderedUI = new Visualizer();
      e.printStackTrace();
    }
    ui = renderedUI;
  }

  public JComponent getRenderedComponent() {
    return ui;
  }
}
