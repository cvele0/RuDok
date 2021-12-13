package state.slot;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SlotStateManager {
  private SlotState currentSlotState;
  private SlotState beforeCurrentSlotState;

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
    this.beforeCurrentSlotState = this.selectSlotState;
  }

  public void setSelectSlotState() {
    this.beforeCurrentSlotState = this.currentSlotState;
    this.currentSlotState = this.selectSlotState;
  }

  public void setAddSlotState() {
    this.beforeCurrentSlotState = this.currentSlotState;
    this.currentSlotState = this.addSlotState;
  }

  public void setRemoveSlotState() {
    this.beforeCurrentSlotState = this.currentSlotState;
    this.currentSlotState = this.removeSlotState;
  }

  public void setMoveSlotState() {
    this.beforeCurrentSlotState = this.currentSlotState;
    this.currentSlotState = this.moveSlotState;
  }
}
