package model.workspace;

import lombok.Getter;
import lombok.Setter;
import observer.IPublisher;
import observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class RuNode implements IPublisher {
  private String name;
  private RuNode parent;

  public RuNode(RuNode parent) {
    this.parent = parent;
  }

  private List<ISubscriber> subscribers;

  public RuNode(RuNode parent, String name) {
    this.parent = parent;
    this.name = name;
  }

  public void setName(String name) {
    this.name = name;
    notifySubscribers(this);
  }

  @Override
  public String toString() {
    return name;
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