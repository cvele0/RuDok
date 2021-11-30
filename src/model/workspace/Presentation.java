package model.workspace;

import lombok.Getter;
import lombok.Setter;
import observer.IPublisher;
import observer.ISubscriber;
import view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class Presentation extends RuNodeComposite {
  private String authorName = "Unknown";
  private URL imageURL;

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
}