package controller.edit;

import model.workspace.Presentation;
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
      ((Presentation) (this.slideView.getSlide().getParent())).startMouseClick(slideView, position);
    }
  }
}
