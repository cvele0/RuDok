package controller.edit;

import model.workspace.Presentation;
import model.workspace.Slot;
import view.MainFrame;
import view.RectangleSlotView;
import view.SlideView;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseClickController extends MouseInputAdapter {
  private SlideView slideView;
  public static RectangleSlotView lastSelectedRectangleSlotView = null;
  private boolean recognizeObject;

  public MouseClickController(SlideView slideView) {
    this.slideView = slideView;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    Point position = e.getPoint();
    RectangleSlotView selected = determineRectangleSlotView(position);

    if (selected != null) { // edit slot (move, remove)
      Slot slot = selected.getSlot();
      ((Presentation) slot.getParent().getParent()).startMouseClick(slot.getParent(), slot, position);
    } else { // add slot
      ((Presentation) slideView.getSlide().getParent()).startMouseClick(slideView.getSlide(), null, position);
    }
    this.recognizeObject = false;
    MainFrame.getInstance().repaint();
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {
      Point position = e.getPoint();

      RectangleSlotView selected = determineRectangleSlotView(position);
      if (selected != null) {
        lastSelectedRectangleSlotView = selected; // better feel while adding components
        ((Presentation) selected.getSlot().getParent().getParent()).startMouseClick(
                selected.getSlot().getParent(), selected.getSlot(), position);
      } else {
        ((Presentation) slideView.getSlide().getParent()).startMouseClick(slideView.getSlide(), null, position);
      }
      MainFrame.getInstance().repaint();
    }
    this.recognizeObject = false;
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    Point position = e.getPoint();

    if (lastSelectedRectangleSlotView.elementAt(position)) recognizeObject = true;
    if (!recognizeObject) return;

    Slot slot = lastSelectedRectangleSlotView.getSlot();
    if (slot != null) {
      ((Presentation) slot.getParent().getParent()).startMouseClick(slot, position);
    }
    MainFrame.getInstance().repaint();
  }

  private RectangleSlotView determineRectangleSlotView(Point position) {
    RectangleSlotView selected = null;
    for (int i = slideView.getRectangles().size() - 1; i >= 0; i--) {
      if (slideView.getRectangles().get(i).elementAt(position)) {
        selected = slideView.getRectangles().get(i);
        break;
      }
    }
    return selected;
  }
}
