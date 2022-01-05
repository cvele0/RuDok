package gui.swing.tree.commands;

import gui.swing.tree.MyTreeNode;
import lombok.Getter;
import lombok.Setter;
import model.*;
import view.MainFrame;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.util.List;

@Getter
@Setter

public abstract class AbstractCommand {
  private MyTreeNode myTreeNode;
  private List<MyTreeNode> toBeExecuted;
  private boolean doneFirst;

  public abstract void doCommand();
  public abstract void undoCommand();

  public AbstractCommand(MyTreeNode myTreeNode) {
    this.myTreeNode = myTreeNode;
  }

  void removeNodesDfs(MyTreeNode u, MyTreeNode origin) {
    if (u.getRuNode() == origin.getRuNode()) {
      toBeExecuted.add(u);
      return;
    }
    for (MyTreeNode v : u.getProjects()) {
      removeNodesDfs(v, origin);
    }
  }

  void removeNodes() {
    for (MyTreeNode myTreeNode : toBeExecuted) {
      RuNode parent = ((MyTreeNode) myTreeNode.getParent()).getRuNode();
      RuNode child = myTreeNode.getRuNode();
      if (!doneFirst) {
        ((RuNodeComposite) parent).removeChild(child);
      }
      if (child instanceof Slide) doneFirst = true;
      MainFrame.getInstance().getWorkspaceTree().removeProject(myTreeNode);
    }
    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
  }

  void addNodes() {
    for (MyTreeNode myTreeNode : toBeExecuted) {
      RuNode parent = ((MyTreeNode) myTreeNode.getParent()).getRuNode();
      RuNode child = myTreeNode.getRuNode();
      if (!doneFirst) {
        ((RuNodeComposite) parent).addChild(child);
      }
      if (child instanceof Slide) doneFirst = true;
      MainFrame.getInstance().getWorkspaceTree().addProject(myTreeNode);
      MainFrame.getInstance().getWorkspaceTree().expandPath(new TreePath(myTreeNode.getPath()));
    }
    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
  }
}
