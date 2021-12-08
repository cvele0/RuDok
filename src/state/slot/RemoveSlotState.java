package state.slot;

import model.workspace.Slide;
import model.workspace.Slot;

import java.awt.*;

public class RemoveSlotState implements SlotState {
  @Override
  public void mouseClick(Slide slide, Slot slot, Point position) {
    slide.removeSlot(slot);
  }

  @Override
  public void mouseClick(Slot slot, Point position) {

  }
}