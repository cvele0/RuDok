package controller.edit;

import model.Presentation;
import model.Slot;
import view.MainFrame;
import view.rectangle.SlideRectangleSlotView;
import view.SlideView;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseClickController extends MouseInputAdapter {
  private SlideView slideView;
  private static SlideRectangleSlotView lastSelectedSlideRectangleSlotView = null;
  private boolean recognizeObject;

  public MouseClickController(SlideView slideView) {
    this.slideView = slideView;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {
      Point position = e.getPoint();
      SlideRectangleSlotView selected = determineRectangleSlotView(position);

      ((Presentation) this.slideView.getSlide().getParent()).setLastSelectedSlideView(
              (((Presentation) this.slideView.getSlide().getParent()).getChildren().indexOf(slideView.getSlide()))
      );

      if (selected != null) { // edit slot (move, remove)
        Slot slot = selected.getSlot();
        ((Presentation) slot.getParent().getParent()).startMouseClick(slot.getParent(), slot, position);
      } else { // add slot
        ((Presentation) slideView.getSlide().getParent()).startMouseClick(slideView.getSlide(), null, position);
      }
      MainFrame.getInstance().repaint();
    }
    this.recognizeObject = false;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {
      Point position = e.getPoint();
      SlideRectangleSlotView selected = determineRectangleSlotView(position);

      ((Presentation) this.slideView.getSlide().getParent()).setLastSelectedSlideView(
              (((Presentation) this.slideView.getSlide().getParent()).getChildren().indexOf(slideView.getSlide()))
      );

      if (selected != null) {
        lastSelectedSlideRectangleSlotView = selected; // better feel while adding components
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

    if (lastSelectedSlideRectangleSlotView == null) return;
    if (lastSelectedSlideRectangleSlotView.elementAt(position)) recognizeObject = true;
    if (!recognizeObject) return;

    Slot slot = lastSelectedSlideRectangleSlotView.getSlot();
    if (slot != null) {
      ((Presentation) slot.getParent().getParent()).startMouseClick(slot, position);
    }
    MainFrame.getInstance().repaint();
  }

  private SlideRectangleSlotView determineRectangleSlotView(Point position) {
    SlideRectangleSlotView selected = null;
    for (int i = slideView.getRectangles().size() - 1; i >= 0; i--) {
      if (slideView.getRectangles().get(i).elementAt(position)) {
        selected = slideView.getRectangles().get(i);
        break;
      }
    }
    return selected;
  }
}
