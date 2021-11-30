package controller.actions;

import view.MainFrame;

import java.awt.event.ActionEvent;

public class ExitSlideshowAction extends AbstractRudokAction {
  public ExitSlideshowAction() {
    putValue(SMALL_ICON, loadIcon("images/exitSlideshow25x25.png"));
    putValue(NAME, "Exit Slide Show");
    putValue(SHORT_DESCRIPTION, "Exit Slide Show");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    MainFrame.getInstance().startEditState();
    MainFrame.getInstance().showContent();
  }
}
