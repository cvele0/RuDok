package model.workspace;

import lombok.Getter;
import lombok.Setter;
import observer.IPublisher;
import observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class Presentation extends RuNodeComposite implements IPublisher {
  private String authorName;
  private URL imageURL;

  private List<ISubscriber> subscribers;

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

  @Override
  public void addSubscriber(ISubscriber sub) {
    if (sub == null)
      return;
    if (this.subscribers == null)
      this.subscribers = new ArrayList<>();
    if (this.subscribers.contains(sub))
      return;
    this.subscribers.add(sub);
  }

  @Override
  public void removeSubscriber(ISubscriber sub) {
    if (sub == null || this.subscribers == null || !this.subscribers.contains(sub))
      return;
    this.subscribers.remove(sub);
  }

  @Override
  public void notifySubscribers(Object notification) {
    if (notification == null || this.subscribers == null || this.subscribers.isEmpty())
      return;
    for (ISubscriber listener : subscribers) {
      listener.update(notification);
    }
  }
}
