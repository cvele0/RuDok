package model;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter

public class Presentation extends RuNodeComposition {
  private String author;
  private ImagePanel image; //TODO load image

  class ImagePanel extends JPanel {
    private Image img;

    public ImagePanel(String img) {
      this(new ImageIcon(img).getImage());
    }

    public ImagePanel(Image img) {
      this.img = img;
    }

    public void paintComponent(Graphics g) {
      g.drawImage(img, (int) (this.getSize().getWidth() - img.getWidth(null)) / 2,
              (int) (this.getSize().getHeight() - img.getHeight(null)) / 2, null);
    }
  }
}
