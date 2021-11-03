package view;

import controller.ActionManager;
import gui.swing.Menu;
import gui.swing.Toolbar;
import gui.swing.tree.WorkspaceTree;
import lombok.Getter;
import gui.swing.tree.WorkspaceModel;

import javax.swing.*;
import java.awt.*;

@Getter

public class MainFrame extends JFrame {
  private static MainFrame instance = null;

  private ActionManager actionManager;
  private JPanel rightPanel = new JPanel();
  private JPanel leftPanel = new JPanel();

  private WorkspaceTree workspaceTree;
  private WorkspaceModel workspaceModel;

  private Toolbar toolbar;
  private Menu menu;

  private MainFrame() {}

  private void initialize() {
    initializeWorkspaceTree();
    initializeGUI();
  }

  private void initializeGUI() {
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

    JScrollPane jScrollPane = new JScrollPane(workspaceTree, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    jScrollPane.setMinimumSize(new Dimension(100, 300));
    //jScrollPane.setPreferredSize(new Dimension(300,300));

    //leftPanel.setPreferredSize(new Dimension(300,300));
    rightPanel.setBackground(Color.LIGHT_GRAY);
    rightPanel.setMinimumSize(new Dimension(300, 300));

    JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jScrollPane, rightPanel);
    jSplitPane.setOneTouchExpandable(true);
    jSplitPane.setDividerLocation(200);

    add(jSplitPane, BorderLayout.CENTER);
  }

  private void initializeWorkspaceTree() {
    workspaceTree = new WorkspaceTree();
    workspaceModel = new WorkspaceModel();
    workspaceTree.setModel(workspaceModel);
  }

  public static MainFrame getInstance() {
    if (instance == null) {
      instance = new MainFrame();
      instance.initialize();
    }
    return instance;
  }
}
