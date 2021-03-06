package controller.edit;

import model.Presentation;
import model.Slot;
import state.slot.AddSlotState;
import state.slot.SelectSlotState;
import view.MainFrame;
import view.popups.SelectSlotTypeDialog;
import view.rectangle.NormalRectangleSlotView;
import view.SlideView;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseClickController extends MouseInputAdapter {
  private SlideView slideView;
  private static NormalRectangleSlotView lastSelectedNormalRectangleSlotView = null;
  private boolean recognizeObject;

  public MouseClickController(SlideView slideView) {
    this.slideView = slideView;
  }

  private void startEditContent(NormalRectangleSlotView selected) {
    if (selected.getSlot().getType() == null) {
      SelectSlotTypeDialog selectSlotTypeDialog = new SelectSlotTypeDialog(selected);
      selectSlotTypeDialog.setVisible(true);
    }
    if (selected.getSlot().getType() != null) {
      selected.getSlotHandler().readContent();
    }
  }

  private void performClick(NormalRectangleSlotView selected, MouseEvent e, boolean isClicked) {
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
      MainFrame.getInstance().setChangedProject(true);
    } else { // add slot, basic click
      ((Presentation) slideView.getSlide().getParent()).startMouseClick(slideView.getSlide(), null, position);
      if (MainFrame.getInstance().getLastSelectedPresentation() != null &&
          MainFrame.getInstance().getLastSelectedPresentation().getCurrentSlotState() instanceof AddSlotState) {
        MainFrame.getInstance().setChangedProject(true);
      }
    }
    MainFrame.getInstance().repaint();
    //MainFrame.getInstance().setLastSelected(slideView.getSlide());
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {
      Point position = e.getPoint();
      NormalRectangleSlotView selected = determineRectangleSlotView(position);

      performClick(selected, e, true);
    }
    this.recognizeObject = false;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {
      Point position = e.getPoint();
      NormalRectangleSlotView selected = determineRectangleSlotView(position);
      if (selected != null) {
        lastSelectedNormalRectangleSlotView = selected;
      }

      performClick(selected, e, false);
    }
    this.recognizeObject = false;
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    Point position = e.getPoint();

    if (lastSelectedNormalRectangleSlotView == null) return;
    if (lastSelectedNormalRectangleSlotView.elementAt(position)) this.recognizeObject = true;
    if (!this.recognizeObject) return;

    Slot slot = lastSelectedNormalRectangleSlotView.getSlot();
    if (slot != null) {
      ((Presentation) slot.getParent().getParent()).startMouseClick(slot, position);
    }
    MainFrame.getInstance().repaint();
  }

  private NormalRectangleSlotView determineRectangleSlotView(Point position) {
    NormalRectangleSlotView selected = null;
    for (int i = slideView.getRectangles().size() - 1; i >= 0; i--) {
      if (slideView.getRectangles().get(i).elementAt(position)) {
        selected = slideView.getRectangles().get(i);
        break;
      }
    }
    return selected;
  }
}
