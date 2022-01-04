package gui.swing.tree.commands;

import gui.swing.tree.MyTreeNode;
import model.Workspace;
import view.MainFrame;

public class RenameNodeCommand extends AbstractCommand {
  private String oldName;
  private String newName;

  public RenameNodeCommand(MyTreeNode myTreeNode, String newName) {
    super(myTreeNode);
    this.newName = newName;
    this.oldName = myTreeNode.getRuNode().getName();
  }

  private void setChanged() {
    if (getMyTreeNode().getRuNode() instanceof Workspace) {
      getMyTreeNode().setChanged(true);
    } else {
      MainFrame.getInstance().setChangedProject(true);
    }
  }

  @Override
  public void doCommand() {
    getMyTreeNode().getRuNode().setName(newName);
    setChanged();
  }

  @Override
  public void undoCommand() {
    getMyTreeNode().getRuNode().setName(oldName);
    setChanged();
  }
}