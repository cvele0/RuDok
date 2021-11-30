package controller.actions;

import error.ErrorFactory;
import state.SlideshowState;
import view.InfoDialog;
import view.MainFrame;

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
    if (MainFrame.getInstance().getStateManager().getCurrentState() instanceof SlideshowState) {
      ErrorFactory.getInstance().generateError(this, "Invalid action in slide show view.");
      return;
    }
    InfoDialog infoDialog = new InfoDialog();
    infoDialog.setVisible(true);
  }
}
