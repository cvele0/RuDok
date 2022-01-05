package gui.swing.tree.commands;

import gui.swing.tree.MyTreeNode;
import lombok.Getter;
import model.*;
import view.MainFrame;

import java.util.ArrayList;

@Getter

public class RemoveNodeCommand extends AbstractCommand {
  private MyTreeNode myParent;

  public RemoveNodeCommand(MyTreeNode myTreeNode) {
    super(myTreeNode);
    setToBeExecuted(new ArrayList<>());
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

    MainFrame.getInstance().setLastSelected(ruNode.getParent());
    getToBeExecuted().clear();
    removeNodesDfs((MyTreeNode) MainFrame.getInstance().getWorkspaceModel().getRoot(), getMyTreeNode());
    setDoneFirst(false);
    removeNodes();
    setChanged();
  }

  @Override
  public void undoCommand() {
    MainFrame.getInstance().setLastSelected(getMyTreeNode().getRuNode());
    setDoneFirst(false);
    addNodes();
    setChanged();
  }
}