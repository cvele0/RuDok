package model.workspace;

import lombok.Getter;
import lombok.Setter;
import observer.IPublisher;
import observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class Project extends RuNodeComposite implements IPublisher {
  public static int projectCounter = 0;
  private int serialNumber;

  private List<ISubscriber> subscribers;

  public Project(RuNode parent, String name) {
    super(parent, name);
  }

  public Project(RuNode parent) {
    super(parent);
  }

  @Override
  public String toString() {
    return getName();
  }

  @Override
  public void addChild(RuNode ruNode) {
    if (ruNode instanceof Presentation) {
      getChildren().add(ruNode);
      notifySubscribers(this);
    }
  }

  @Override
  public void removeChild(RuNode ruNode) {
    if (ruNode instanceof Presentation) {
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
