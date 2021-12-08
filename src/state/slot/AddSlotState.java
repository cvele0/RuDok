package state.slot;

import lombok.Getter;
import lombok.Setter;
import model.workspace.Slide;
import model.workspace.Slot;

import java.awt.*;

@Setter
@Getter

public class AddSlotState implements SlotState {
  private Color color;

  public AddSlotState() {
    this.color = Color.RED; // Default color
  }

  @Override
  public void mouseClick(Slide slide, Slot slot, Point position) {
    Slot newSlot = new Slot(position);
    newSlot.setColor(this.color);
    newSlot.setDimension(new Dimension(50, 50));
    newSlot.setStroke(new BasicStroke(3f));
    newSlot.setParent(slide);  //newSlot.setPaint(Color.BLUE);
    slide.addSlot(newSlot);
  }
}