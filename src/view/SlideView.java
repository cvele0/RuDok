package view;

import model.workspace.Slide;
import observer.ISubscriber;

import javax.swing.*;
import java.awt.*;

public class SlideView extends JPanel implements ISubscriber {
  private Slide slide;

  public SlideView() {
    this.slide = null;
    initialize();
    update(this);
  }

  public SlideView(Slide slide) {
    this.slide = slide;
    if (this.slide != null) {
      this.slide.addSubscriber(this);
    }
    initialize();
    update(this);
  }

  public void setSlide(Slide slide) {
    if (this.slide != null) {
      this.slide.getSubscribers().remove(this);
    }
    this.slide = slide;
    if (this.slide != null) {
      this.slide.addSubscriber(this);
    }
    update(this);
  }

  private void initialize() {
    setBackground(Color.RED);
    setSize(new Dimension(50, 50));
    setMaximumSize(new Dimension(50, 50));
  }

  @Override
  public void update(Object notification) {
    /*removeAll();
    if (presentation != null) {
      elementViewList.clear();
      for (RuNode item : presentation.getChildren()) {
        ElementView elementView = new ElementView((Slide) item);
        elementViewList.add(elementView);
        add(elementView);
        add(Box.createRigidArea(new Dimension(5, 0)));
      }
    }*/
  }
}
