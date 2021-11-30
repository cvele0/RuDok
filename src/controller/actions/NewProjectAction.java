package controller.actions;

import error.ErrorFactory;
import gui.swing.tree.MyTreeNode;
import model.workspace.*;
import state.SlideshowState;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class NewProjectAction extends AbstractRudokAction {
  public NewProjectAction() {
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
    putValue(SMALL_ICON, loadIcon("images/new25x25.png"));
    putValue(NAME, "New Project");
    putValue(SHORT_DESCRIPTION, "New Project");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (MainFrame.getInstance().getStateManager().getCurrentState() instanceof SlideshowState) {
      ErrorFactory.getInstance().generateError(this, "Invalid action in slide show view.");
      return;
    }

    MyTreeNode myTreeNode = (MyTreeNode) MainFrame.getInstance().getWorkspaceTree().getLastSelectedPathComponent();

    if (myTreeNode == null) {
      ErrorFactory.getInstance().generateError(this, "Please select a node.");
      return;
    }

    RuNode ruNode = myTreeNode.getRuNode();
    if (ruNode instanceof Slide) {
      ruNode = MainFrame.getInstance().getLastSelectedSlide();
    } else if (ruNode instanceof Presentation) {
      ruNode = MainFrame.getInstance().getLastSelectedPresentation();
    } else if (ruNode instanceof Project) {
      ruNode = MainFrame.getInstance().getLastSelectedProject();
    }
    if (ruNode == null) {
      ErrorFactory.getInstance().generateError(this, "Please select a node.");
      return;
    }

    int size = myTreeNode.getChildCount();

    if (ruNode instanceof Workspace) {
      Project project = new Project(ruNode);
      project.setSerialNumber(++Project.projectCounter);
      project.setName("Project " + project.getSerialNumber());

      ((Workspace) ruNode).addChild(project);

      MyTreeNode newNode = new MyTreeNode(project);
      newNode.setParent(myTreeNode);

      MainFrame.getInstance().getWorkspaceTree().addProject(newNode);
    }
    else if (ruNode instanceof Project) {
      Presentation presentation = new Presentation(ruNode, "Presentation " + (size + 1));
      ((Project) ruNode).addChild(presentation);

      MyTreeNode node = new MyTreeNode(presentation);
      node.setParent(myTreeNode);

      MainFrame.getInstance().getWorkspaceTree().addProject(node);
    }
    else if (ruNode instanceof Presentation) {
      RuNode slide = new Slide(ruNode, "Slide " + (size + 1));
      ((Presentation) ruNode).addChild(slide);

      MyTreeNode node = new MyTreeNode(slide);
      node.setParent(myTreeNode);
      MainFrame.getInstance().getWorkspaceTree().addProject(node);

      MainFrame.getInstance().refresh();
    } else if (ruNode instanceof Slide) {
      ErrorFactory.getInstance().generateError(this, "Cannot add any more nodes.");
    }
  }
}