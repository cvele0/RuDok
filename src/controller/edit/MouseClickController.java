package controller.edit;

import model.workspace.Presentation;
import model.workspace.Slot;
import view.MainFrame;
import view.RectangleSlotView;
import view.SlideView;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseClickController extends MouseAdapter {
  private SlideView slideView;

  public MouseClickController(SlideView slideView) {
    this.slideView = slideView;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {
      Point position = e.getPoint();

      RectangleSlotView selected = null;
      for (int i = slideView.getRectangles().size() - 1; i >= 0; i--) {
        if (slideView.getRectangles().get(i).elementAt(position)) {
          selected = slideView.getRectangles().get(i);
          break;
        }
      }

      if (selected != null) { // edit slot (move, delete)
        Slot slot = selected.getSlot();
        ((Presentation) slot.getParent().getParent()).startMouseClick(slot.getParent(), slot, position);
      } else { // add slot
        ((Presentation) slideView.getSlide().getParent()).startMouseClick(slideView.getSlide(), null, position);
      }
      MainFrame.getInstance().refresh();
    }
  }
}
