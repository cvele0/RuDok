package gui.swing.tree;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;

public class WorkspaceTree extends JTree {
  public WorkspaceTree() {
    addTreeSelectionListener(new WorkspaceTreeController());
    setCellEditor(new WorkspaceTreeCellEditor(this, new DefaultTreeCellRenderer()));
    setCellRenderer(new WorkspaceTreeCellRenderer());
    setEditable(true);
  }

  public void addProject(MyTreeNode myTreeNode) {
    ((WorkspaceModel) getModel()).addProject(myTreeNode);
    SwingUtilities.updateComponentTreeUI(this);
  }

  public void removeProject(MyTreeNode myTreeNode) {
    ((WorkspaceModel) getModel()).removeProject(myTreeNode);
    SwingUtilities.updateComponentTreeUI(this);
  }
}
