package gui.swing.tree;

import model.workspace.*;
import view.MainFrame;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public class WorkspaceModel extends DefaultTreeModel {
  public WorkspaceModel() {
    super(new MyTreeNode(new Workspace(null, "Workspace")));
  }

  public void addProject(MyTreeNode myTreeNode) {
    MyTreeNode lastSelected = (MyTreeNode) MainFrame.getInstance().getWorkspaceTree().getLastSelectedPathComponent();
    lastSelected.addProject(myTreeNode);
  }
}
