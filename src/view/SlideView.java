package view;

import model.workspace.Presentation;
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
    jLabel.setBackground(Color.RED);

    setBackground(Color.RED);
    setPreferredSize(new Dimension(350, 250));
    setMaximumSize(new Dimension(350, 250));
    setLayout(new BorderLayout());

    jLabel.setPreferredSize(new Dimension(350, 250));
    jLabel.setMaximumSize(new Dimension(350, 250));

    add(jLabel, BorderLayout.CENTER);
  }

  @Override
  public void update(Object notification) {
    URL imageURL = null;
    if (this.slide != null) {
      imageURL = ((Presentation) this.slide.getParent()).getImageURL();
    }

    ImageIcon imageIcon = null;
    if (imageURL != null) {
      imageIcon = new ImageIcon(imageURL);
    } else {
      //System.err.println("Error loading image");
      //TODO throw an error
    }

    if (imageIcon != null) {
      jLabel.setIcon(imageIcon);
    }

    removeAll();
    add(jLabel, BorderLayout.CENTER);
  }
}

/*
URL imageURL = getClass().getResource("images/japan.jpg");

    ImageIcon imageIcon = null;
    if (imageURL != null) {
      imageIcon = new ImageIcon(imageURL);
    } else {
      System.err.println("Greska");
    }
    if (imageIcon != null) {
      jLabel.setIcon(imageIcon);
    }
 */