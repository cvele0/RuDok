package state.slot;

import model.workspace.Slide;
import model.workspace.Slot;

import java.awt.*;

public class MoveSlotState implements SlotState {
  @Override
  public void mouseClick(Slide slide, Slot slot, Point position) {
    System.out.println("Move 3 arg");
  }

  @Override
  public void mouseClick(Slot slot, Point position) {
    slot.getPosition().translate(position.x - slot.getPosition().x, position.y - slot.getPosition().y);
    //slot.setPosition(position);
  }
}
