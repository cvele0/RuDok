package controller.actions;

import error.ErrorFactory;
import gui.swing.tree.MyTreeNode;
import gui.swing.tree.commands.RemoveNodeCommand;
import model.*;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RemoveProjectAction extends AbstractRudokAction {
  public RemoveProjectAction() {
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
    putValue(SMALL_ICON, loadIcon("images/remove25x25.png"));
    putValue(NAME, "Remove Project");
    putValue(SHORT_DESCRIPTION, "Remove Project");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    MyTreeNode myTreeNode = (MyTreeNode) MainFrame.getInstance().getWorkspaceTree().getLastSelectedPathComponent();

    if (checkForErrors(myTreeNode)) return;

    RuNode ruNode = myTreeNode.getRuNode();
    if (ruNode instanceof Workspace) {
      ErrorFactory.getInstance().generateError(this, "Cannot delete a workspace.");
      return;
    }

    MainFrame.getInstance().getWorkspaceTree().getCommandInvoker().addCommand(new RemoveNodeCommand(myTreeNode));
  }
}
