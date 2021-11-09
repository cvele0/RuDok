package controller.actions;

import gui.swing.tree.MyTreeNode;
import model.workspace.*;
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
    MyTreeNode myTreeNode = (MyTreeNode) MainFrame.getInstance().getWorkspaceTree().getLastSelectedPathComponent();

    if (myTreeNode == null) return;

    RuNode ruNode = myTreeNode.getRuNode();

    if (ruNode instanceof Project) {
      //.getInstance().getProjectView().setProject(null);
      ((RuNodeComposite) ruNode.getParent()).removeChild(ruNode);
      MainFrame.getInstance().getWorkspaceTree().removeProject(myTreeNode);
      MainFrame.getInstance().setLastSelectedProject(null);
    } else if (ruNode instanceof Presentation) {
      //MainFrame.getInstance().getProjectView().getPresentationView().setPresentation(null);
      ((RuNodeComposite) ruNode.getParent()).removeChild(ruNode);
      MainFrame.getInstance().getWorkspaceTree().removeProject(myTreeNode);
      MainFrame.getInstance().refresh();
      //MainFrame.getInstance().setLastSelectedPresentation(null);
    } else if (ruNode instanceof Slide) {
      ((RuNodeComposite) ruNode.getParent()).removeChild(ruNode);
      MainFrame.getInstance().getWorkspaceTree().removeProject(myTreeNode);
      MainFrame.getInstance().refresh();
    }
  }
}