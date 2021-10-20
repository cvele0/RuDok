package view;

import controller.ActionManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
  private static MainFrame instance = null;

  private ActionManager actionManager;
  private JPanel leftPanel = new JPanel();
  private JPanel rightPanel = new JPanel();
  Toolbar toolbar;
  Menu menu;

  private MainFrame() {}

  private void initialize() {
    actionManager = new ActionManager();

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

    leftPanel.setBackground(Color.RED);
    leftPanel.setSize(1000, 1000);
    leftPanel.setPreferredSize(new Dimension(300,300));
    rightPanel.setBackground(Color.BLUE);

    add(leftPanel, BorderLayout.WEST);
    add(rightPanel, BorderLayout.CENTER);
  }

  public static MainFrame getInstance() {
    if (instance == null) {
      instance = new MainFrame();
      instance.initialize();
    }
    return instance;
  }

  public ActionManager getActionManager() {
    return actionManager;
  }
}
