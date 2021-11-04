package gui.swing.tree;

import model.workspace.Project;
import model.workspace.RuNode;
import view.MainFrame;
import view.ProjectView;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class WorkspaceTreeController implements TreeSelectionListener {
  @Override
  public void valueChanged(TreeSelectionEvent e) {
    TreePath path = e.getPath();

    for (int i = 0; i < path.getPathCount(); i++) {
      MyTreeNode myTreeNode = (MyTreeNode) path.getPathComponent(i);
      RuNode ruNode = myTreeNode.getRuNode();

      if (ruNode instanceof Project) {
        MainFrame.getInstance().getProjectView().setProject((Project) ruNode);
        ((Project) ruNode).notifySubscribers(this);
      }
    }
  }
}
