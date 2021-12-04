package state.slot;

import lombok.Getter;
import lombok.Setter;
import model.workspace.Slot;
import view.MainFrame;
import view.SlideView;

import java.awt.*;

@Setter
@Getter

public class AddSlotState implements SlotState {
  private Color color;

  public AddSlotState() {
    this.color = Color.RED; // Default color
  }

  @Override
  public void mouseClick(SlideView slideView, Point position) {
    Slot slot = new Slot(position);
    slot.setColor(this.color);
    slot.setDimension(new Dimension(50, 50));
    slot.setStroke(new BasicStroke(3f));
    slot.setParent(slideView.getSlide());
    //slot.setPaint(Color.BLUE);

    slideView.getSlide().addSlot(slot);
    MainFrame.getInstance().refresh();
  }
}
