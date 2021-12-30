package controller.actions;

import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class UndoAction extends AbstractRudokAction {
  public UndoAction() {
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
    putValue(SMALL_ICON, loadIcon("images/undo25x25.png"));
    putValue(NAME, "Undo");
    putValue(SHORT_DESCRIPTION, "Undo Action");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    MainFrame.getInstance().getWorkspaceTree().getCommandManager().undoCommand();
  }
}
