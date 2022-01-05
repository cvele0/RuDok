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
    add(MainFrame.getInstance().getActionManager().getOpenProjectAction());
    add(MainFrame.getInstance().getActionManager().getNewProjectAction());
    add(MainFrame.getInstance().getActionManager().getRemoveProjectAction());
    add(MainFrame.getInstance().getActionManager().getEditInfoAction());
    add(MainFrame.getInstance().getActionManager().getSharePresentationAction());
    add(MainFrame.getInstance().getActionManager().getSaveProjectAction());
    add(MainFrame.getInstance().getActionManager().getUndoAction());
    add(MainFrame.getInstance().getActionManager().getRedoAction());
    add(MainFrame.getInstance().getActionManager().getInfoAction());
  }

  public void setEditTextMode() {
    removeAll();
    add(MainFrame.getInstance().getActionManager().getBoldAction());
    add(MainFrame.getInstance().getActionManager().getItalicAction());
    add(MainFrame.getInstance().getActionManager().getUnderlineAction());
    add(MainFrame.getInstance().getActionManager().getSaveContentAction());
  }

  public void setEditMultimediaMode() {
    removeAll();
    add(MainFrame.getInstance().getActionManager().getOpenFileAction());
    add(MainFrame.getInstance().getActionManager().getSaveContentAction());
  }
}
