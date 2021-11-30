package state;

import view.MainFrame;

public class EditState implements State {
  @Override
  public void showContent() {
    MainFrame.getInstance().getContentPane().remove(MainFrame.getInstance().getSlideshowView());
    MainFrame.getInstance().getContentPane().add(MainFrame.getInstance().getJSplitPane());
    MainFrame.getInstance().getToolbar().setEditMode();
    MainFrame.getInstance().repaint();
  }
}
