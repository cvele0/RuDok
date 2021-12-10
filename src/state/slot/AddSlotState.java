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
    newSlot.setDimension(new Dimension(50, 50));
    newSlot.setInterruptedStroke(interruptedStroke);
    newSlot.setLineWidth(lineWidth);

    if (!isInterruptedStroke()) {
      newSlot.setStroke(new BasicStroke((float) getLineWidth(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
    } else {
      newSlot.setStroke(new BasicStroke((float) getLineWidth(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
              2f, new float[]{7}, 0.0f));
    }

    newSlot.setParent(slide);  //newSlot.setPaint(Color.BLUE);
    slide.addSlot(newSlot);
  }

  @Override
  public void mouseClick(Slot slot, Point position) {

  }
}