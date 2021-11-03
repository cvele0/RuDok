package gui.swing;

import view.MainFrame;

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

    JMenu helpMenu = new JMenu("Help");
    helpMenu.setMnemonic(KeyEvent.VK_H);
    helpMenu.add(MainFrame.getInstance().getActionManager().getInfoAction());

    this.add(fileMenu);
    this.add(editMenu);
    this.add(helpMenu);
  }
}
