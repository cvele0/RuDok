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

    this.add(fileMenu);
    this.add(helpMenu);
  }
}
