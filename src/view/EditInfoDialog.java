package view;

import javax.swing.*;
import java.awt.*;

public class EditInfoDialog extends JDialog {
  public EditInfoDialog() {
    super(MainFrame.getInstance(), "Edit info", true);
    initialize();
    addItems();
  }

  private void initialize() {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    int screenHeight = screenSize.height * 3 / 8;
    int screenWidth = screenSize.width / 4;

    //Image image = toolkit.getImage("src/images/avatar.png");
    //setIconImage(image);

    setSize(new Dimension(screenWidth, screenHeight));
    setLocationRelativeTo(MainFrame.getInstance());

    //setLayout(new BorderLayout());
  }

  private void addItems() {

  }
}
