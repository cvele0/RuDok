package view;

import controller.edit.ChangeAuthorName;
import controller.edit.ChangeBackground;

import javax.swing.*;
import java.awt.*;

public class EditInfoDialog extends JDialog {
  private JButton changeAuthorNameBtn;
  private JButton changeBackground1;
  private JButton changeBackground2;
  private JButton changeBackground3;
  private JButton changeBackground4;

  public EditInfoDialog() {
    super(MainFrame.getInstance(), "Edit info", true);
    initialize();
    addItems();
  }

  private void initialize() {
    changeAuthorNameBtn = new JButton("Change author name");
    changeBackground1 = new JButton("Default Background");
    changeBackground2 = new JButton("Background 1 (Japan)");
    changeBackground3 = new JButton("Background 2 (Moscow)");
    changeBackground4 = new JButton("Background 3 (Nature)");


    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    int screenHeight = screenSize.height * 3 / 8;
    int screenWidth = screenSize.width / 4;

    setSize(new Dimension(screenWidth, screenHeight));
    setLocationRelativeTo(MainFrame.getInstance());
    setLayout(new GridLayout(5, 1, 10, 10));
  }

  private void addItems() {
    add(changeAuthorNameBtn);
    add(changeBackground1);
    add(changeBackground2);
    add(changeBackground3);
    add(changeBackground4);

    changeAuthorNameBtn.addActionListener(new ChangeAuthorName());
    changeBackground1.addActionListener(new ChangeBackground(this));
    changeBackground2.addActionListener(new ChangeBackground(this));
    changeBackground3.addActionListener(new ChangeBackground(this));
    changeBackground4.addActionListener(new ChangeBackground(this));
  }
}
