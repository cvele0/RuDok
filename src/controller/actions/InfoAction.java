package controller.actions;

import view.popups.InfoDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class InfoAction extends AbstractRudokAction {
  public InfoAction() {
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
    putValue(SMALL_ICON, loadIcon("images/info25x25.png"));
    putValue(NAME, "Info");
    putValue(SHORT_DESCRIPTION, "Info");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    InfoDialog infoDialog = new InfoDialog();
    infoDialog.setVisible(true);
  }
}
