package model.workspace;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter

public class Presentation extends RuNodeComposite {
  private String author;
  private ImagePanel image; //TODO load image

  public Presentation(RuNode parent, String name) {
    super(parent, name);
  }

  @Override
  public void addChild(RuNode ruNode) {
    if (ruNode instanceof Slide) {
      getChildren().add(ruNode);
    } else {
      //TODO throw an error
    }
  }

  @Override
  public void removeChild(RuNode ruNode) {
    if (ruNode instanceof Slide) {
      getChildren().remove(ruNode);
    } else {
      //TODO throw an error
    }
  }

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
