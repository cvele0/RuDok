package state.slot;

import model.Slide;
import model.Slot;

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