package view;

import controller.edit.ChangeAuthorName;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EditInfoDialog extends JDialog {
  private JButton changeAuthorNameBtn;
  private JButton changeBackground1;
  private JButton changeBackground2;
  private JButton changeBackground3;

  public EditInfoDialog() {
    super(MainFrame.getInstance(), "Edit info", true);
    initialize();
    addItems();
  }

  private void initialize() {
    changeAuthorNameBtn = new JButton("Change author name");
    changeBackground1 = new JButton("Background 1 (Japan)");
    changeBackground2 = new JButton("Background 2 (Moscow)");
    changeBackground3 = new JButton("Background 3 (Nature)");

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    int screenHeight = screenSize.height * 3 / 8;
    int screenWidth = screenSize.width / 4;

    setSize(new Dimension(screenWidth, screenHeight));
    setLocationRelativeTo(MainFrame.getInstance());
    setLayout(new GridLayout(4, 1, 10, 10));
  }

  private void addItems() {
    add(changeAuthorNameBtn);
    add(changeBackground1);
    add(changeBackground2);
    add(changeBackground3);

    changeAuthorNameBtn.addActionListener(new ChangeAuthorName());
  }
}
