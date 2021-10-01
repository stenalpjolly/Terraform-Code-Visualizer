package com.stenalpjolly.tfcv.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class Visualizer extends JPanel {

  public Visualizer() {
    renderEmptyComponent();
  }

  private void renderEmptyComponent() {
    setLayout(new MigLayout());
    JLabel comp = new JLabel("There is no supported entity available to display.");
    add(comp, "push, align center");
  }
}
