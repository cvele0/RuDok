package controller.actions;

import view.EditInfoDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EditInfoAction extends AbstractRudokAction {
  public EditInfoAction() {
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
    putValue(SMALL_ICON, loadIcon("images/editInfo25x25.png"));
    putValue(NAME, "Edit Info");
    putValue(SHORT_DESCRIPTION, "Edit Info");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    EditInfoDialog editInfoDialog = new EditInfoDialog();
    editInfoDialog.setVisible(true);
  }
}
