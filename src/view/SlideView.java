package view;

import model.workspace.Slide;

import javax.swing.*;
import java.awt.*;

public class SlideView extends JPanel {
  private Slide slide;

  public SlideView(Slide slide) {
    this.slide = slide;
    initialize();
  }

  public SlideView() {}

  private void initialize() {
    setBackground(Color.RED);
  }
}
