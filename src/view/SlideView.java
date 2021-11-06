package view;

import model.workspace.Slide;
import observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SlideView extends JPanel implements ISubscriber {
  private Slide slide;

  private JLabel jLabel;

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
    jLabel = new JLabel();

    setBackground(Color.RED);
    setPreferredSize(new Dimension(250, 250));
    setMaximumSize(new Dimension(250, 250));
    setLayout(new BorderLayout());

    jLabel.setPreferredSize(new Dimension(250, 250));
    jLabel.setMaximumSize(new Dimension(250, 250));

    add(jLabel, BorderLayout.CENTER);
  }

  @Override
  public void update(Object notification) {
    removeAll();

    URL imageURL = getClass().getResource("images/japan.jpg");

    ImageIcon imageIcon = null;
    if (imageURL != null) {
      imageIcon = new ImageIcon(imageURL);
    } else {
      System.err.println("Greska");
    }
    jLabel.setIcon(imageIcon);
    add(jLabel, BorderLayout.CENTER);
  }
}