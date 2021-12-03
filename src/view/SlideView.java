package view;

import lombok.Getter;
import model.workspace.Presentation;
import model.workspace.Slide;
import observer.ISubscriber;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

@Getter

public class SlideView extends JPanel implements ISubscriber {
  private Slide slide;

  private JLabel nameLabel;

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
      this.slide.removeSubscriber(this);
    }
    this.slide = slide;
    if (this.slide != null) {
      this.slide.addSubscriber(this);
    }
    update(this);
  }

  private void initialize() {
    nameLabel = new JLabel();

    //centerPanel.setBackground(new Color(236, 149, 62));
    setPreferredSize(new Dimension(400, 300));
    setMaximumSize(new Dimension(400, 300));
    setLayout(new BorderLayout());

    nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
    nameLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
    nameLabel.setForeground(Color.BLACK);
  }

  @Override
  public void update(Object notification) {
    if (this.slide != null) {
      nameLabel.setText(this.slide.getName());
    } else {
      nameLabel.setText("Default");
    }

    removeAll();
    if (this.slide != null) {
      add(new WorkPanel(((Presentation) this.slide.getParent()).getImageURL()), BorderLayout.CENTER);
    }
    add(nameLabel, BorderLayout.NORTH);
  }

  class WorkPanel extends JPanel {
    private Image img;

    public WorkPanel(URL url) {
      this.img = new ImageIcon(url).getImage();
    }

    public void paintComponent(Graphics g) {
      g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }
  }
}