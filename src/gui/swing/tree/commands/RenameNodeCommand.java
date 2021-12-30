package gui.swing.tree.commands;

import gui.swing.tree.MyTreeNode;
import view.MainFrame;

import javax.swing.*;

public class RenameNodeCommand extends AbstractCommand {
  private String oldName;
  private String newName;

  public RenameNodeCommand(MyTreeNode myTreeNode, String newName) {
    super(myTreeNode);
    this.newName = newName;
    this.oldName = myTreeNode.getRuNode().getName();
  }

  @Override
  public void doCommand() {
    getMyTreeNode().getRuNode().setName(newName);
    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
    MainFrame.getInstance().refresh();
  }

  @Override
  public void undoCommand() {
    getMyTreeNode().getRuNode().setName(oldName);
    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
    MainFrame.getInstance().refresh();
  }
}
