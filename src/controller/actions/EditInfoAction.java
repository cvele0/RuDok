package controller.actions;

import error.ErrorFactory;
import state.slideshow.SlideshowState;
import view.EditInfoDialog;
import view.MainFrame;

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
    if (MainFrame.getInstance().getLastSelectedPresentation() == null) {
      ErrorFactory.getInstance().generateError(this, "Please select a presentation");
      return;
    }
    if (MainFrame.getInstance().getStateManager().getCurrentState() instanceof SlideshowState) {
      ErrorFactory.getInstance().generateError(this, "Invalid action in slide show view.");
      return;
    }

    EditInfoDialog editInfoDialog = new EditInfoDialog();
    editInfoDialog.setVisible(true);
  }
}
