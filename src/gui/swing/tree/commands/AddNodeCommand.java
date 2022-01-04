package gui.swing.tree.commands;

import gui.swing.tree.MyTreeNode;
import lombok.Getter;
import model.*;
import model.factory.AbstractNodeFactory;
import model.factory.FactoryGenerator;
import view.MainFrame;

@Getter

public class AddNodeCommand extends AbstractCommand {
  private MyTreeNode myChild;

  public AddNodeCommand(MyTreeNode myTreeNode) {
    super(myTreeNode);
  }

  private void setChanged() {
    MainFrame.getInstance().setChangedProject(true);
    if (myChild.getRuNode() instanceof Project) {
      getMyTreeNode().setChanged(true);
    }
  }

  @Override
  public void doCommand() {
    if (myChild == null) {
      // Factory method - node insertion
      RuNode ruNode = getMyTreeNode().getRuNode();

      int size = getMyTreeNode().getChildCount();
      AbstractNodeFactory ANF = FactoryGenerator.returnFactory(ruNode);
      MyTreeNode child = new MyTreeNode(ANF.getNodeForTree(ruNode, size + 1));
      child.setParent(getMyTreeNode());
      this.myChild = child;

      MainFrame.getInstance().setLastSelected(child.getRuNode());
    } else {
      RuNode child = getMyChild().getRuNode();
      RuNode parent = getMyTreeNode().getRuNode();

      ((RuNodeComposite) parent).addChild(child);
      MainFrame.getInstance().setLastSelected(child);
    }
    MainFrame.getInstance().getWorkspaceTree().addProject(myChild);
    setChanged();
  }

  @Override
  public void undoCommand() {
    //Node deletion
    RuNode ruNode = getMyChild().getRuNode();

    if (ruNode instanceof Project) {
      MainFrame.getInstance().setLastSelectedProject(null);
    } else if (ruNode instanceof Presentation) {
      MainFrame.getInstance().setLastSelectedPresentation(null);
    } else if (ruNode instanceof Slide) {
      MainFrame.getInstance().setLastSelectedSlide(null);
    }

    MainFrame.getInstance().setLastSelected(ruNode);
    ((RuNodeComposite) ruNode.getParent()).removeChild(ruNode);
    MainFrame.getInstance().getWorkspaceTree().removeProject(getMyChild());
    setChanged();
  }
}