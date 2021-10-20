package controller;

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
    //TODO action
  }
}
