package view;

import controller.actions.ActionManager;
import error.ErrorFactory;
import error.MyError;
import gui.swing.tree.WorkspaceTree;
import lombok.Getter;
import gui.swing.tree.WorkspaceModel;
import lombok.Setter;
import model.workspace.Presentation;
import model.workspace.Project;
import model.workspace.Slide;
import observer.ISubscriber;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter

public class MainFrame extends JFrame implements ISubscriber {
  private static MainFrame instance = null;

  private ActionManager actionManager;

  private ProjectView projectView;
  private SlideshowView slideshowView;
  private JSplitPane jSplitPane;

  private WorkspaceTree workspaceTree;
  private WorkspaceModel workspaceModel;

  private Project lastSelectedProject;
  private Presentation lastSelectedPresentation;
  private Slide lastSelectedSlide;

  private Toolbar toolbar;
  private Menu menu;

  private MainFrame() {}

  private void initialize() {
    lastSelectedProject = null;
    lastSelectedPresentation = null;
    lastSelectedSlide = null;

    ErrorFactory.getInstance().addSubscriber(this);

    initializeWorkspaceTree();
    initializeGUI();
    addElements();
  }

  private void initializeGUI() {
    actionManager = new ActionManager();
    projectView = new ProjectView();
    slideshowView = null;

    // MainFrame size
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    //MainFrame specifications
    setSize(screenWidth / 2, screenHeight / 2);
    Image image = toolkit.getImage("src/images/icon.png");
    setIconImage(image);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Rudok");
  }

  private void addElements() {
    //Toolbar and Menu
    menu = new Menu();
    setJMenuBar(menu);
    toolbar = new Toolbar();
    add(toolbar, BorderLayout.NORTH);

    // Elements
    JScrollPane jScrollPane = new JScrollPane(workspaceTree,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    jScrollPane.setMinimumSize(new Dimension(100, 300));

    jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jScrollPane, projectView);
    jSplitPane.setOneTouchExpandable(true);
    jSplitPane.setDividerLocation(200);

    this.getContentPane().add(jSplitPane, BorderLayout.CENTER);
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

  public void setLastSelectedProject(Project lastSelectedProject) {
    this.lastSelectedProject = lastSelectedProject;
    this.lastSelectedPresentation = null;
    this.lastSelectedSlide = null;
    refresh();
  }

  public void setLastSelectedPresentation(Presentation lastSelectedPresentation) {
    if (lastSelectedPresentation != null) {
      this.lastSelectedPresentation = lastSelectedPresentation;
      this.lastSelectedProject = (Project) this.lastSelectedPresentation.getParent();
      this.lastSelectedSlide = null;
    } else {
      this.lastSelectedPresentation = null;
      this.lastSelectedSlide = null;
    }
    refresh();
  }

  public void setLastSelectedSlide(Slide lastSelectedSlide) {
    if (lastSelectedSlide != null) {
      this.lastSelectedSlide = lastSelectedSlide;
      this.lastSelectedPresentation = (Presentation) this.lastSelectedSlide.getParent();
      this.lastSelectedProject = (Project) this.lastSelectedPresentation.getParent();
    } else {
      this.lastSelectedSlide = null;
    }
    refresh();
  }

  public void refresh() {
    this.getProjectView().setProject(lastSelectedProject);
  }

  @Override
  public void update(Object notification) {
    JOptionPane.showMessageDialog(this, ((MyError) notification).getMessage());
  }
}