package state;

import view.MainFrame;
import view.SlideshowView;

public class SlideshowState implements State {
  @Override
  public void showContent() {
    MainFrame.getInstance().setSlideshowView(new SlideshowView(MainFrame.getInstance().getLastSelectedPresentation()));
    MainFrame.getInstance().getContentPane().remove(MainFrame.getInstance().getJSplitPane());
    MainFrame.getInstance().getContentPane().add(MainFrame.getInstance().getSlideshowView());
    MainFrame.getInstance().getToolbar().setSlideshowMode();
    MainFrame.getInstance().repaint();
  }
}
