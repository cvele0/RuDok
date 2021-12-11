package model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class Slide extends RuNode {
  private int pageNumber;
  private List<Slot> slots;

  private boolean selectedThumbnail;

  public Slide() {
    slots = new ArrayList<>();
    this.selectedThumbnail = false;
  }

  public void setSelectedThumbnail(boolean selectedThumbnail) {
    this.selectedThumbnail = selectedThumbnail;
    notifySubscribers(this);
  }

  @Override
  public void setName(String name, int size) {
    setName("Slide " + size);
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