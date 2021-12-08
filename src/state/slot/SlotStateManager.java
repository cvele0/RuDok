package state.slot;

import lombok.Getter;

@Getter

public class SlotStateManager {
  private SlotState currentSlotState;

  private SelectSlotState selectSlotState;
  private AddSlotState addSlotState;
  private RemoveSlotState removeSlotState;
  private MoveSlotState moveSlotState;

  public SlotStateManager() {
    initialize();
  }

  private void initialize() {
    selectSlotState = new SelectSlotState();
    addSlotState = new AddSlotState();
    removeSlotState = new RemoveSlotState();
    moveSlotState = new MoveSlotState();
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

  public void setMoveSlotState() {
    this.currentSlotState = this.moveSlotState;
  }
}
