package controller.actions;

import error.ErrorFactory;
import gui.swing.tree.MyTreeNode;
import model.Presentation;
import view.MainFrame;
import view.popups.SharePresentationDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class SharePresentationAction extends AbstractRudokAction {
  public SharePresentationAction() {
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
    putValue(SMALL_ICON, loadIcon("images/share25x25.png"));
    putValue(NAME, "Share presentation");
    putValue(SHORT_DESCRIPTION, "Share presentation");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    MyTreeNode myTreeNode = (MyTreeNode) MainFrame.getInstance().getWorkspaceTree().getLastSelectedPathComponent();
    if (myTreeNode == null || (!(myTreeNode.getRuNode() instanceof Presentation))) {
      ErrorFactory.getInstance().generateError(this, "Please select a presentation to share.");
      return;
    }

    /*if (((Presentation) myTreeNode.getRuNode()).isShared()) {
      ErrorFactory.getInstance().generateError(this, "Cannot share a reference.");
      return;
    }*/

    SharePresentationDialog sharePresentationDialog = new SharePresentationDialog((Presentation) myTreeNode.getRuNode());
    sharePresentationDialog.setVisible(true);
  }
}
