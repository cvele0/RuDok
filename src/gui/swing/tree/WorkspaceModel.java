package gui.swing.tree;

import model.Workspace;
import view.MainFrame;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class WorkspaceModel extends DefaultTreeModel {
  public WorkspaceModel() {
    super(new MyTreeNode(new Workspace()));
  }

  public void addProject(MyTreeNode myTreeNode) {
    ((MyTreeNode) myTreeNode.getParent()).addProject(myTreeNode);
    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
  }

  public void removeProject(MyTreeNode myTreeNode) {
    ((MyTreeNode) myTreeNode.getParent()).removeProject(myTreeNode);
    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
  }
}
