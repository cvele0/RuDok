package gui.swing.tree;

import model.Workspace;

import javax.swing.tree.DefaultTreeModel;

public class WorkspaceModel extends DefaultTreeModel {
  public WorkspaceModel() {
    super(new MyTreeNode(new Workspace()));
  }

  public void addProject(MyTreeNode myTreeNode) {
    ((MyTreeNode) myTreeNode.getParent()).addProject(myTreeNode);
  }

  public void removeProject(MyTreeNode myTreeNode) {
    ((MyTreeNode) myTreeNode.getParent()).removeProject(myTreeNode);
  }
}
