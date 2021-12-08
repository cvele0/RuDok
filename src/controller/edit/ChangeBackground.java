package controller.edit;

import view.EditInfoDialog;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class ChangeBackground implements ActionListener {
  private EditInfoDialog editInfoDialog;

  public ChangeBackground(EditInfoDialog editInfoDialog) {
    this.editInfoDialog = editInfoDialog;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JButton jButton = (JButton) e.getSource();

    URL imageURL = null;

    if (jButton.getText().contains("Japan")) {
      imageURL = getClass().getResource("images/japan.jpg");
    } else if (jButton.getText().contains("Moscow")) {
      imageURL = getClass().getResource("images/moscow.jpg");
    } else if (jButton.getText().contains("Nature")) {
      imageURL = getClass().getResource("images/nature.jpg");
    } else if (jButton.getText().contains("Default")) {
      imageURL = getClass().getResource("images/default1.jpg");
    }

    if (imageURL != null) {
      MainFrame.getInstance().getLastSelectedPresentation().setImageURL(imageURL);
      MainFrame.getInstance().refresh();
    }

    editInfoDialog.setVisible(false);
  }
}