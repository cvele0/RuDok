package view;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Menu extends JMenuBar {
  public Menu() {
    JMenu fileMenu = new JMenu("File");
    fileMenu.setMnemonic(KeyEvent.VK_F);
    fileMenu.add(MainFrame.getInstance().getActionManager().getNewProjectAction());

    JMenu helpMenu = new JMenu("Help");
    helpMenu.setMnemonic(KeyEvent.VK_H);
    helpMenu.add(MainFrame.getInstance().getActionManager().getInfoAction());

    JMenu editMenu = new JMenu("Edit");
    editMenu.setMnemonic(KeyEvent.VK_E);
    editMenu.add(MainFrame.getInstance().getActionManager().getEditInfoAction());

    this.add(fileMenu);
    this.add(helpMenu);
    this.add(editMenu);
  }
}
