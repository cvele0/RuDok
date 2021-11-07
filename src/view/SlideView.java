package view;

import error.ErrorFactory;
import model.workspace.Presentation;
import model.workspace.Slide;
import observer.ISubscriber;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class SlideView extends JPanel implements ISubscriber {
  private Slide slide;

  private JLabel jLabel;
  private JLabel nameLabel;

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
    nameLabel = new JLabel();
    jLabel.setBackground(Color.RED);

    setBackground(new Color(236, 149, 62));
    setPreferredSize(new Dimension(350, 250));
    setMaximumSize(new Dimension(350, 250));
    setLayout(new BorderLayout());

    jLabel.setLayout(new BorderLayout());
    jLabel.setPreferredSize(new Dimension(350, 250));
    jLabel.setMaximumSize(new Dimension(350, 250));

    nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
    nameLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
    nameLabel.setForeground(Color.WHITE);

    jLabel.add(nameLabel, BorderLayout.NORTH);
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
    }

    jLabel.setIcon(imageIcon);
    if (imageIcon == null) {
      jLabel.setText("       ");
      nameLabel.setText("Default");
    } else {
      jLabel.setText("");
      nameLabel.setText(this.slide.getName());
    }

    removeAll();
    add(jLabel, BorderLayout.CENTER);
  }
}