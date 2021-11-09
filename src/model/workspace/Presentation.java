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
  private String authorName = "Unknown";
  private URL imageURL;

  private List<ISubscriber> subscribers;

  public Presentation(RuNode parent, String name) {
    super(parent, name);
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
    notifySubscribers(this);
  }

  public void setImageURL(URL imageURL) {
    this.imageURL = imageURL;
    notifySubscribers(this);
  }

  @Override
  public void addChild(RuNode ruNode) {
    if (ruNode instanceof Slide) {
      getChildren().add(ruNode);
      notifySubscribers(this);
    }
  }

  @Override
  public void removeChild(RuNode ruNode) {
    if (ruNode instanceof Slide) {
      getChildren().remove(ruNode);
      notifySubscribers(this);
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