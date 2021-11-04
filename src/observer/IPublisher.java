package observer;

public interface IPublisher {
  void addSubscriber(ISubscriber sub);
  void removeSubscriber(ISubscriber sub);
  void notifySubscribers(Object notification);

  /*
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
 */
}
