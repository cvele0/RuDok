package gui.swing.tree;

import model.Workspace;
import view.MainFrame;

import javax.swing.tree.DefaultTreeModel;

public class WorkspaceModel extends DefaultTreeModel {
  public WorkspaceModel() {
    super(new MyTreeNode(new Workspace()));
  }

  public void addProject(MyTreeNode myTreeNode) {
    MyTreeNode lastSelected = (MyTreeNode) MainFrame.getInstance().getWorkspaceTree().getLastSelectedPathComponent();
    lastSelected.addProject(myTreeNode);
  }

  public void removeProject(MyTreeNode myTreeNode) {
    MyTreeNode lastSelected = (MyTreeNode) MainFrame.getInstance().getWorkspaceTree().getLastSelectedPathComponent();
    ((MyTreeNode) lastSelected.getParent()).removeProject(lastSelected);
  }
}
