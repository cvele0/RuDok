package view;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Menu extends JMenuBar {
  public Menu() {
    JMenu fileMenu = new JMenu("File");
    fileMenu.setMnemonic(KeyEvent.VK_F);
    fileMenu.add(MainFrame.getInstance().getActionManager().getNewProjectAction());

    this.add(fileMenu);
  }
}
