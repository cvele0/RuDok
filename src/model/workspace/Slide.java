package model.workspace;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class Slide extends RuNode {
  private int pageNumber;
  private List<Slot> slots;

  public Slide(RuNode parent, String name) {
    super(parent, name);
    slots = new ArrayList<>();
  }

  public void addSlot(Slot slot) {
    slots.add(slot);
    notifySubscribers(this);
  }

  public void removeSlot(Slot slot) {
    slots.remove(slot);
    notifySubscribers(this);
  }
}
