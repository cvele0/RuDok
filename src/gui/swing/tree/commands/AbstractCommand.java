package gui.swing.tree.commands;

import gui.swing.tree.MyTreeNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public abstract class AbstractCommand {
  private MyTreeNode myTreeNode;

  public abstract void doCommand();
  public abstract void undoCommand();

  public AbstractCommand(MyTreeNode myTreeNode) {
    this.myTreeNode = myTreeNode;
  }
}
