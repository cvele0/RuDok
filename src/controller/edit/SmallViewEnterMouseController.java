package controller.edit;

import view.MainFrame;
import view.PresentationView;
import view.SmallSlideView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SmallViewEnterMouseController implements MouseListener {
  private PresentationView presentationView;
  private SmallSlideView smallSlideView;
  private int indexNumber;

  public SmallViewEnterMouseController(PresentationView presentationView, SmallSlideView smallSlideView, int indexNumber) {
    this.presentationView = presentationView;
    this.smallSlideView = smallSlideView;
    this.indexNumber = indexNumber;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    this.presentationView.getPresentation().setLastSelectedSlideView(indexNumber);
    MainFrame.getInstance().refresh();
  }

  @Override
  public void mousePressed(MouseEvent e) {
    this.presentationView.getPresentation().setLastSelectedSlideView(indexNumber);
    MainFrame.getInstance().refresh();
  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {
    this.smallSlideView.getSlide().setSelectedThumbnail(true);
    MainFrame.getInstance().refresh();
  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
