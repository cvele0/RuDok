package state.slot;

import view.MainFrame;
import view.RectangleSlotView;
import view.SlideView;

import java.awt.*;

public class RemoveSlotState implements SlotState {

  @Override
  public void mouseClick(SlideView slideView, Point position) {
    RectangleSlotView toBeDeleted = null;
    for (int i = slideView.getRectangles().size() - 1; i >= 0; i--) {
      if (slideView.getRectangles().get(i).elementAt(position)) {
        toBeDeleted = slideView.getRectangles().get(i);
        break;
      }
    }
    if (toBeDeleted != null) {
      toBeDeleted.getSlot().getParent().removeSlot(toBeDeleted.getSlot());
      MainFrame.getInstance().refresh();
    }
  }
}
