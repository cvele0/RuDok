package controller.actions;

import gui.swing.tree.MyTreeNode;
import model.*;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;

public class OpenProjectAction extends AbstractRudokAction {
  public OpenProjectAction() {
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
    putValue(SMALL_ICON, loadIcon("images/openProject25x25.png"));
    putValue(NAME, "Open Project");
    putValue(SHORT_DESCRIPTION, "Open Project");
  }

  public void createNodeForProject(Project project) {
    if (project == null) return;
    boolean setLastSelected = false;

    MyTreeNode newProjectNode = new MyTreeNode(project);
    newProjectNode.setParent((MyTreeNode) MainFrame.getInstance().getWorkspaceModel().getRoot());
    newProjectNode.getRuNode().setParent(((MyTreeNode) newProjectNode.getParent()).getRuNode());
    ((RuNodeComposite) ((MyTreeNode) newProjectNode.getParent()).getRuNode()).addChild(newProjectNode.getRuNode());
    MainFrame.getInstance().getWorkspaceTree().addProject(newProjectNode);

    for (RuNode presentation : project.getChildren()) {
      MyTreeNode newPresentationNode = new MyTreeNode(presentation);
      newPresentationNode.setParent(newProjectNode);
      MainFrame.getInstance().getWorkspaceTree().addProject(newPresentationNode);

      for (RuNode slide : ((Presentation) presentation).getChildren()) {
        MyTreeNode newSlideNode = new MyTreeNode(slide);
        if (!setLastSelected) MainFrame.getInstance().setLastSelected(slide);
        setLastSelected = true;
        newSlideNode.setParent(newPresentationNode);
        MainFrame.getInstance().getWorkspaceTree().addProject(newSlideNode);
      }
    }
  }

  private Project loadProjectFromFile(File file) {
    Project project = null;
    try {
      ObjectInputStream oos = new ObjectInputStream(new FileInputStream(file));
      try {
        project = (Project) oos.readObject();
        if (project != null) {
          project.setProjectFile(file); // For some reason, project file is lost during deserialization
        }
      } catch (ClassNotFoundException classNotFoundException) {
        System.err.println("Invalid project resource.");
      }
    } catch (IOException ioException) {
      System.err.println("Invalid project resource.");
    }
    return project;
  }

  public void loadWorkspace(Workspace workspace) {
    JFileChooser jFileChooser = new JFileChooser();
    jFileChooser.setFileFilter(new SaveProjectAction.WorkspaceFileFilter());

    boolean loadedSuccessfully = false;

    if (jFileChooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
      try {
        BufferedReader br = new BufferedReader(new FileReader(jFileChooser.getSelectedFile()));
        String line;
        while (true) {
          try {
            if ((line = br.readLine()) == null) break;
            File file = new File(line);
            Project project = loadProjectFromFile(file);
            createNodeForProject(project);
            loadedSuccessfully = true;
            //if (project != null) System.out.println(project.getProjectFile().getAbsolutePath());
          } catch (IOException e) {
            System.err.println("Invalid workspace resource.");
          }
        }
      } catch (FileNotFoundException fileNotFoundException) {
        System.err.println("Invalid workspace resource.");
      }
    }
    if (!loadedSuccessfully) {
      System.exit(0);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JFileChooser jFileChooser = new JFileChooser();
    jFileChooser.setFileFilter(new SaveProjectAction.ProjectFileFilter());

    if (jFileChooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
      Project project = loadProjectFromFile(jFileChooser.getSelectedFile());
      if (project == null) return;
      createNodeForProject(project);
      ((MyTreeNode) MainFrame.getInstance().getWorkspaceModel().getRoot()).setChanged(true);
    }
  }
}
