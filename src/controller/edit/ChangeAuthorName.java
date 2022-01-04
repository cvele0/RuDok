package controller.edit;

import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeAuthorName implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    String authorName = JOptionPane.showInputDialog("Enter new author name: ");
    if (MainFrame.getInstance().getLastSelectedPresentation() != null) {
      MainFrame.getInstance().getLastSelectedPresentation().setAuthorName(authorName);
      MainFrame.getInstance().setChangedProject(true);
      MainFrame.getInstance().refresh();
    }
  }
}
