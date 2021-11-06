package model.workspace;

import lombok.Getter;
import lombok.Setter;
import observer.IPublisher;
import observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class Slide extends RuNode implements IPublisher {
  private int pageNumber;

  List<ISubscriber> subscribers;

  public Slide(RuNode parent, String name) {
    super(parent, name);
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
