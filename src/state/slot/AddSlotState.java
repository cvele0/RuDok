package state.slot;

import lombok.Getter;
import lombok.Setter;
import model.Slide;
import model.Slot;

import java.awt.*;

@Setter
@Getter

public class AddSlotState implements SlotState {
  private Color color;
  private boolean interruptedStroke;
  private int lineWidth;

  public AddSlotState() {
    this.color = Color.RED; // Default settings
    this.interruptedStroke = false;
    this.lineWidth = 3;
  }

  @Override
  public void mouseClick(Slide slide, Slot slot, Point position) {
    Slot newSlot = new Slot(position);
    newSlot.setColor(this.color);
    newSlot.setInterruptedStroke(interruptedStroke);
    newSlot.setLineWidth(lineWidth);
    newSlot.setParent(slide);
    slide.addSlot(newSlot);
  }

  @Override
  public void mouseClick(Slot slot, Point position) {

  }
}