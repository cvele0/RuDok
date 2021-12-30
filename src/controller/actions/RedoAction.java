package controller.actions;

import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RedoAction extends AbstractRudokAction {
  public RedoAction() {
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
    putValue(SMALL_ICON, loadIcon("images/redo25x25.png"));
    putValue(NAME, "Redo");
    putValue(SHORT_DESCRIPTION, "Redo Action");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    MainFrame.getInstance().getWorkspaceTree().getCommandManager().doCommand();
  }
}
