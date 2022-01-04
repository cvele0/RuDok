package controller.actions;

import error.ErrorFactory;
import gui.swing.tree.MyTreeNode;
import gui.swing.tree.commands.AddNodeCommand;
import model.*;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class NewProjectAction extends AbstractRudokAction {
  public NewProjectAction() {
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
    putValue(SMALL_ICON, loadIcon("images/new25x25.png"));
    putValue(NAME, "New Project");
    putValue(SHORT_DESCRIPTION, "New Project");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    MyTreeNode myTreeNode = (MyTreeNode) MainFrame.getInstance().getWorkspaceTree().getLastSelectedPathComponent();

    if (checkForErrors(myTreeNode)) return;

    RuNode ruNode = myTreeNode.getRuNode();
    if (ruNode instanceof Slide) {
      ErrorFactory.getInstance().generateError(this, "Cannot add any more nodes.");
      return;
    }

    MainFrame.getInstance().getWorkspaceTree().getCommandManager().addCommand(new AddNodeCommand(myTreeNode));
  }
}