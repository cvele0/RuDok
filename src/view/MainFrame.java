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

    JScrollPane jScrollPane = new JScrollPane(leftPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    jScrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    jScrollPane.setMinimumSize(new Dimension(100, 200));

    leftPanel.setPreferredSize(new Dimension(1000,300));
    leftPanel.setBackground(Color.RED);
    rightPanel.setBackground(Color.BLUE);
    rightPanel.setMinimumSize(new Dimension(300, 200));

    JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jScrollPane, rightPanel);
    jSplitPane.setOneTouchExpandable(true);
    jSplitPane.setDividerLocation(300);

    add(jSplitPane, BorderLayout.CENTER);
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
