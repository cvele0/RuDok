package error;

import observer.IPublisher;
import observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;

public class ErrorFactory implements IPublisher {
  private static ErrorFactory instance = null;

  private List<ISubscriber> subscribers;

  private ErrorFactory() {}

  public void generateError(Object type, String message) {
    MyError myError = new MyError(type, message);
    notifySubscribers(myError);
  }

  public static ErrorFactory getInstance() {
    if (instance == null) {
      instance = new ErrorFactory();
    }
    return instance;
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
