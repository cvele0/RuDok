package controller.actions;

import error.ErrorFactory;
import state.SlideshowState;
import view.MainFrame;

import java.awt.event.ActionEvent;

public class SlideshowAction extends AbstractRudokAction {
  public SlideshowAction() {
    putValue(SMALL_ICON, loadIcon("images/slideshow25x25.png"));
    putValue(NAME, "Slide Show");
    putValue(SHORT_DESCRIPTION, "Slide Show");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (MainFrame.getInstance().getLastSelectedPresentation() == null) {
      ErrorFactory.getInstance().generateError(this, "Please select a presentation for the Slide Show.");
      return;
    }
    if (MainFrame.getInstance().getStateManager().getCurrentState() instanceof SlideshowState) {
      ErrorFactory.getInstance().generateError(this, "Invalid action in slide show view.");
      return;
    }
    MainFrame.getInstance().startSlideshowState();
    MainFrame.getInstance().showContent();
  }
}
