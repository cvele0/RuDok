package gui.swing.tree;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class WorkspaceTreeController implements TreeSelectionListener {
  @Override
  public void valueChanged(TreeSelectionEvent e) {
    TreePath path = e.getPath();

    for (int i = 0; i < path.getPathCount(); i++) {
      MyTreeNode myTreeNode = (MyTreeNode) path.getPathComponent(i);
      System.out.println("Selektovan dijagram:" + myTreeNode.getRuNode());

      System.out.println("getPath: " + e.getPath());
      System.out.println("getPath: " + e.getNewLeadSelectionPath());
      System.out.println("getName: " + myTreeNode.getRuNode().getName());
    }
  }
}
