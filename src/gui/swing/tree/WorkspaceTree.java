package gui.swing.tree;

import gui.swing.tree.commands.CommandInvoker;
import lombok.Getter;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;

@Getter

public class WorkspaceTree extends JTree {
  private CommandInvoker commandInvoker;

  public WorkspaceTree() {
    commandInvoker = new CommandInvoker();
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
