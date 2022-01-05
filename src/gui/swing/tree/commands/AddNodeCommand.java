package gui.swing.tree.commands;

import gui.swing.tree.MyTreeNode;
import lombok.Getter;
import model.*;
import model.factory.AbstractNodeFactory;
import model.factory.FactoryGenerator;
import view.MainFrame;

import java.util.ArrayList;

@Getter

public class AddNodeCommand extends AbstractCommand {
  private MyTreeNode myChild;

  public AddNodeCommand(MyTreeNode myTreeNode) {
    super(myTreeNode);
    setToBeExecuted(new ArrayList<>());
  }

  private void setChanged() {
    MainFrame.getInstance().setChangedProject(true);
    if (myChild.getRuNode() instanceof Project) {
      getMyTreeNode().setChanged(true);
    }
  }

  private void addSlidesDfs(MyTreeNode u, MyTreeNode origin) {
    if (u.getRuNode() == ((MyTreeNode) origin.getParent()).getRuNode()) {
      MyTreeNode newNode = new MyTreeNode(origin.getRuNode());
      newNode.setParent(u);
      getToBeExecuted().add(newNode);
      return;
    }
    for (MyTreeNode v : u.getProjects()) {
      addSlidesDfs(v, origin);
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

      if (myChild.getRuNode() instanceof Slide) { // Adding slots to shared presentations
        addSlidesDfs((MyTreeNode) MainFrame.getInstance().getWorkspaceModel().getRoot(), myChild);
      } else {
        getToBeExecuted().add(myChild);
      }
    }
    MainFrame.getInstance().setLastSelected(myChild.getRuNode());
    setDoneFirst(false);
    addNodes();
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

    getToBeExecuted().clear();
    removeNodesDfs((MyTreeNode) MainFrame.getInstance().getWorkspaceModel().getRoot(), myChild);
    setDoneFirst(false);
    removeNodes();
    setChanged();
  }
}