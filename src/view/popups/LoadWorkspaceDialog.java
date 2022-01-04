package view.popups;

import lombok.Getter;
import view.MainFrame;

import javax.swing.*;
import java.awt.*;

@Getter

public class LoadWorkspaceDialog extends JDialog {
  private JButton loadBtn;
  private JButton newBtn;

  public static final int EXIT = 0;
  public static final int LOAD = 1;
  public static final int NEW = 2;

  private int selectedOption;

  public LoadWorkspaceDialog() {
    super(MainFrame.getInstance(), "Please select a workspace.", true);
    initialize();
    addItems();
    addListeners();
  }

  private void initialize() {
    loadBtn = new JButton("Load Workspace");
    newBtn = new JButton("New Workspace");
    this.selectedOption = EXIT;

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    int screenHeight = screenSize.height * 3 / 15;
    int screenWidth = screenSize.width / 4;

    setSize(new Dimension(screenWidth, screenHeight));
    setLocationRelativeTo(MainFrame.getInstance());
    setLayout(new GridLayout(2, 1, 10, 10));
  }

  private void addItems() {
    add(loadBtn);
    add(newBtn);
  }

  private void addListeners() {
    loadBtn.addActionListener(e -> {
      this.selectedOption = LOAD;
      setVisible(false);
    });

    newBtn.addActionListener(e -> {
      this.selectedOption = NEW;
      setVisible(false);
    });
  }
}
