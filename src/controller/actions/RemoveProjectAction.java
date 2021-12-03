package controller.actions;

import error.ErrorFactory;
import gui.swing.tree.MyTreeNode;
import model.workspace.*;
import state.slideshow.SlideshowState;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RemoveProjectAction extends AbstractRudokAction {
  public RemoveProjectAction() {
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
    putValue(SMALL_ICON, loadIcon("images/remove25x25.png"));
    putValue(NAME, "Remove Project");
    putValue(SHORT_DESCRIPTION, "Remove Selected Project");
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

    if (ruNode instanceof Workspace) {
      ErrorFactory.getInstance().generateError(this, "Cannot delete a workspace.");
      return;
    } else if (ruNode instanceof Project) {
      ((RuNodeComposite) ruNode.getParent()).removeChild(ruNode);
      MainFrame.getInstance().getWorkspaceTree().removeProject(myTreeNode);
      MainFrame.getInstance().setLastSelectedProject(null);
    } else if (ruNode instanceof Presentation) {
      ((RuNodeComposite) ruNode.getParent()).removeChild(ruNode);
      MainFrame.getInstance().getWorkspaceTree().removeProject(myTreeNode);
      MainFrame.getInstance().setLastSelectedPresentation(null);
    } else if (ruNode instanceof Slide) {
      ((RuNodeComposite) ruNode.getParent()).removeChild(ruNode);
      MainFrame.getInstance().getWorkspaceTree().removeProject(myTreeNode);
      MainFrame.getInstance().setLastSelectedSlide(null);
    }
  }
}
