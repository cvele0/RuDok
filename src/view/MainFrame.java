package view;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
  private static MainFrame instance = null;

  ActionManager actionManager;
  Toolbar toolbar;
  Menu menu;

  private MainFrame() {}

  private void initialize() {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    setSize(screenWidth / 2, screenHeight / 2);
    Image image = toolkit.getImage("src/images/icon.png");
    setIconImage(image);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Rudok");

    menu = new Menu();
    setJMenuBar(menu);

    toolbar = new Toolbar();
    add(toolbar, BorderLayout.NORTH);
  }

  public static MainFrame getInstance() {
    if (instance == null) {
      instance = new MainFrame();
      instance.initialize();
    }
    return instance;
  }
}
