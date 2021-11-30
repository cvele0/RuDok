package view;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Menu extends JMenuBar {
  public Menu() {
    JMenu fileMenu = new JMenu("File");
    fileMenu.setMnemonic(KeyEvent.VK_F);
    fileMenu.add(MainFrame.getInstance().getActionManager().getNewProjectAction());

    JMenu editMenu = new JMenu("Edit");
    editMenu.setMnemonic(KeyEvent.VK_E);
    editMenu.add(MainFrame.getInstance().getActionManager().getRemoveProjectAction());
    editMenu.add(MainFrame.getInstance().getActionManager().getEditInfoAction());

    JMenu viewMenu = new JMenu("View");
    editMenu.setMnemonic(KeyEvent.VK_V);
    viewMenu.add(MainFrame.getInstance().getActionManager().getSlideshowAction());
    viewMenu.add(MainFrame.getInstance().getActionManager().getExitSlideshowAction());

    JMenu helpMenu = new JMenu("Help");
    helpMenu.setMnemonic(KeyEvent.VK_H);
    helpMenu.add(MainFrame.getInstance().getActionManager().getInfoAction());

    this.add(fileMenu);
    this.add(editMenu);
    this.add(viewMenu);
    this.add(helpMenu);
  }
}
