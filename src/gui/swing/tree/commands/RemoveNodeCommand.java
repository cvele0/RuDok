package gui.swing.tree.commands;

import gui.swing.tree.MyTreeNode;
import lombok.Getter;
import model.*;
import view.MainFrame;

@Getter

public class RemoveNodeCommand extends AbstractCommand {
  private MyTreeNode myParent;

  public RemoveNodeCommand(MyTreeNode myTreeNode) {
    super(myTreeNode);
  }

  private void setChanged() {
    if (myParent.getRuNode() instanceof Workspace) {
      myParent.setChanged(true);
    }
    MainFrame.getInstance().setChangedProject(true);
  }

  @Override
  public void doCommand() {
    //Node deletion
    RuNode ruNode = getMyTreeNode().getRuNode();

    if (ruNode instanceof Project) {
      MainFrame.getInstance().setLastSelectedProject(null);
    } else if (ruNode instanceof Presentation) {
      MainFrame.getInstance().setLastSelectedPresentation(null);
    } else if (ruNode instanceof Slide) {
      MainFrame.getInstance().setLastSelectedSlide(null);
    }

    if (this.myParent == null) {
      this.myParent = (MyTreeNode) getMyTreeNode().getParent();
    }

    ((RuNodeComposite) ruNode.getParent()).removeChild(ruNode);
    MainFrame.getInstance().setLastSelected(ruNode.getParent());
    MainFrame.getInstance().getWorkspaceTree().removeProject(getMyTreeNode());
    setChanged();
  }

  @Override
  public void undoCommand() {
    ((RuNodeComposite) getMyParent().getRuNode()).addChild(getMyTreeNode().getRuNode());
    MainFrame.getInstance().getWorkspaceTree().addProject(getMyTreeNode());
    MainFrame.getInstance().setLastSelected(getMyTreeNode().getRuNode());
    setChanged();
  }
}