package controller.edit;

import model.Presentation;
import model.Slot;
import state.slot.SelectSlotState;
import view.MainFrame;
import view.popups.SelectSlotTypeDialog;
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

  private void startEditContent(SlideRectangleSlotView selected) {
    if (selected.getSlot().getType() == null) {
      SelectSlotTypeDialog selectSlotTypeDialog = new SelectSlotTypeDialog(selected);
      selectSlotTypeDialog.setVisible(true);
    }
    if (selected.getSlot().getType() != null) {
      selected.getSlotHandler().readContent();
    }
  }

  private void performClick(SlideRectangleSlotView selected, MouseEvent e, boolean isClicked) {
    Point position = e.getPoint();

    ((Presentation) this.slideView.getSlide().getParent()).setLastSelectedSlideView(
            (((Presentation) this.slideView.getSlide().getParent()).getChildren().indexOf(slideView.getSlide()))
    );

    if (selected != null) { // edit slot (move, remove, edit content)
      Slot slot = selected.getSlot();
      if (((Presentation) slot.getParent().getParent()).getCurrentSlotState() instanceof SelectSlotState) {
        if (isClicked) startEditContent(selected);
      } else {
        ((Presentation) slot.getParent().getParent()).startMouseClick(slot.getParent(), slot, position);
      }
    } else { // add slot
      ((Presentation) slideView.getSlide().getParent()).startMouseClick(slideView.getSlide(), null, position);
    }
    MainFrame.getInstance().repaint();
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {
      Point position = e.getPoint();
      SlideRectangleSlotView selected = determineRectangleSlotView(position);

      performClick(selected, e, true);
    }
    this.recognizeObject = false;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {
      Point position = e.getPoint();
      SlideRectangleSlotView selected = determineRectangleSlotView(position);
      if (selected != null) {
        lastSelectedSlideRectangleSlotView = selected;
      }

      performClick(selected, e, false);
    }
    this.recognizeObject = false;
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    Point position = e.getPoint();

    if (lastSelectedSlideRectangleSlotView == null) return;
    if (lastSelectedSlideRectangleSlotView.elementAt(position)) this.recognizeObject = true;
    if (!this.recognizeObject) return;

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
