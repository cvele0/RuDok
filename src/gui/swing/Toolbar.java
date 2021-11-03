package gui.swing;

import view.MainFrame;

import javax.swing.*;

public class Toolbar extends JToolBar {
  public Toolbar() {
    super(HORIZONTAL);
    setFloatable(false);
    add(MainFrame.getInstance().getActionManager().getNewProjectAction());
    add(MainFrame.getInstance().getActionManager().getInfoAction());
    add(MainFrame.getInstance().getActionManager().getEditInfoAction());
  }
}
