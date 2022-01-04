package controller.actions;

import error.ErrorFactory;
import gui.swing.tree.MyTreeNode;
import model.Project;
import model.RuNode;
import model.Workspace;
import view.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;

public class SaveProjectAction extends AbstractRudokAction {
  public SaveProjectAction() {
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
    putValue(SMALL_ICON, loadIcon("images/saveProject25x25.png"));
    putValue(NAME, "Save Project");
    putValue(SHORT_DESCRIPTION, "Save Project");
  }

  private void saveForWorkspace() {
    Workspace workspace = (Workspace) ((MyTreeNode) MainFrame.getInstance().getWorkspaceModel().getRoot()).getRuNode();

    JFileChooser jFileChooser = new JFileChooser();
    jFileChooser.setFileFilter(new WorkspaceFileFilter());

    File workspaceFile = workspace.getWorkspaceFile();
    if (!workspace.isChanged()) return;
    if (workspaceFile == null) {
      if (jFileChooser.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
        workspaceFile = jFileChooser.getSelectedFile();
      } else {
        return;
      }
    }

    try {
      FileWriter fileWriter = new FileWriter(workspaceFile, false);

      for (RuNode project : workspace.getChildren()) {
        if (((Project) project).getProjectFile() != null) {
          fileWriter.write(((Project) project).getProjectFile().getAbsolutePath());
          fileWriter.append('\n');
        }
      }

      workspace.setWorkspaceFile(workspaceFile);
      workspace.setChanged(false);
      fileWriter.close();
    } catch (IOException e) {
      System.err.println("Invalid workspace resource");
    }
    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
    MainFrame.getInstance().refresh();
  }

  private void saveForProject() {
    JFileChooser jFileChooser = new JFileChooser();
    jFileChooser.setFileFilter(new ProjectFileFilter());

    Project project = MainFrame.getInstance().getLastSelectedProject();

    File projectFile = project.getProjectFile();
    if (!project.isChanged()) return;
    if (projectFile == null) {
      if (jFileChooser.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
        projectFile = jFileChooser.getSelectedFile();
        ((MyTreeNode) MainFrame.getInstance().getWorkspaceModel().getRoot()).setChanged(true);
      } else {
        return;
      }
    }

    ObjectOutputStream oos;
    try {
      oos = new ObjectOutputStream(new FileOutputStream(projectFile));
      oos.writeObject(project);
      project.setProjectFile(projectFile);
      project.setChanged(false);
    } catch (IOException ioException) {
      System.err.println("Invalid project resource.");
    }
    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
    MainFrame.getInstance().refresh();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    MyTreeNode myTreeNode = (MyTreeNode) MainFrame.getInstance().getWorkspaceTree().getLastSelectedPathComponent();
    if (myTreeNode == null) {
      ErrorFactory.getInstance().generateError(this, "Please select what to save.");
      return;
    }

    if (myTreeNode.getRuNode() instanceof Workspace) {
      saveForWorkspace();
    } else {
      saveForProject();
    }
  }

  public static class ProjectFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
      return (f.isDirectory() ||
              f.getName().toLowerCase().endsWith(".rpf"));
    }

    @Override
    public String getDescription() {
      return "Project Files (*.rpf)";
    }
  }

  public static class WorkspaceFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
      return (f.isDirectory() ||
              f.getName().toLowerCase().endsWith(".txt"));
    }

    @Override
    public String getDescription() {
      return "Workspace Files (*.txt)";
    }
  }
}
