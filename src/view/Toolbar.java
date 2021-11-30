package view;

import javax.swing.*;

public class Toolbar extends JToolBar {
  public Toolbar() {
    super(HORIZONTAL);
    setFloatable(false);
    setEditMode();
  }

  public void setEditMode() {
    removeAll();
    add(MainFrame.getInstance().getActionManager().getNewProjectAction());
    add(MainFrame.getInstance().getActionManager().getRemoveProjectAction());
    add(MainFrame.getInstance().getActionManager().getInfoAction());
    add(MainFrame.getInstance().getActionManager().getEditInfoAction());
    add(MainFrame.getInstance().getActionManager().getSlideshowAction());
  }

  public void setSlideshowMode() {
    removeAll();
    add(MainFrame.getInstance().getActionManager().getExitSlideshowAction());
  }
}