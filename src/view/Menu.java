package view;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Menu extends JMenuBar {
  public Menu() {
    JMenu fileMenu = new JMenu("File");
    fileMenu.setMnemonic(KeyEvent.VK_F);
    fileMenu.add(MainFrame.getInstance().getActionManager().getOpenProjectAction());
    fileMenu.add(MainFrame.getInstance().getActionManager().getNewProjectAction());
    fileMenu.add(MainFrame.getInstance().getActionManager().getSharePresentationAction());
    fileMenu.add(MainFrame.getInstance().getActionManager().getSaveProjectAction());

    JMenu editMenu = new JMenu("Edit");
    editMenu.setMnemonic(KeyEvent.VK_E);
    editMenu.add(MainFrame.getInstance().getActionManager().getRemoveProjectAction());
    editMenu.add(MainFrame.getInstance().getActionManager().getUndoAction());
    editMenu.add(MainFrame.getInstance().getActionManager().getRedoAction());
    editMenu.add(MainFrame.getInstance().getActionManager().getEditInfoAction());

    JMenu helpMenu = new JMenu("Help");
    helpMenu.setMnemonic(KeyEvent.VK_H);
    helpMenu.add(MainFrame.getInstance().getActionManager().getInfoAction());

    this.add(fileMenu);
    this.add(editMenu);
    this.add(helpMenu);
  }
}
