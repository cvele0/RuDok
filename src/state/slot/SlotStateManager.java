package state.slot;

import lombok.Getter;

@Getter

public class SlotStateManager {
  private SlotState currentSlotState;
  private SelectSlotState selectSlotState;
  private AddSlotState addSlotState;
  private RemoveSlotState removeSlotState;

  public SlotStateManager() {
    initialize();
  }

  private void initialize() {
    selectSlotState = new SelectSlotState();
    addSlotState = new AddSlotState();
    removeSlotState = new RemoveSlotState();
    this.currentSlotState = this.selectSlotState;
  }

  public void setSelectSlotState() {
    this.currentSlotState = this.selectSlotState;
  }

  public void setAddSlotState() {
    this.currentSlotState = this.addSlotState;
  }

  public void setRemoveSlotState() {
    this.currentSlotState = this.removeSlotState;
  }
}
