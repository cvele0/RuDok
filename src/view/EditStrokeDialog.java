package view;

import lombok.Getter;
import model.Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

@Getter

public class EditStrokeDialog extends JDialog {
  private Presentation presentation;

  private JPanel jPanel;
  private JPanel panel1;
  private JPanel panel2;

  private JLabel lineWidthLbl;
  private JLabel interruptedLbl;

  private JComboBox<Integer> lineWidthCmb;
  private JCheckBox interruptedChb;

  public EditStrokeDialog(Presentation presentation) {
    super(MainFrame.getInstance(), "Stroke settings", true);
    this.presentation = presentation;
    initialize();
    addItems();
    addListeners();
  }

  private void initialize() {
    jPanel = new JPanel();
    panel1 = new JPanel();
    panel2 = new JPanel();

    panel1.setPreferredSize(new Dimension(200, 20));
    panel1.setMaximumSize(new Dimension(200, 20));

    lineWidthLbl = new JLabel("Line width:  ");
    lineWidthCmb = new JComboBox<>();
    for (int i = 1; i <= 15; i++) lineWidthCmb.addItem(i);
    lineWidthCmb.setSelectedItem(this.presentation.getAddSlotStateLineWidth());

    interruptedLbl = new JLabel("Interrupted: ");
    interruptedChb = new JCheckBox();
    interruptedChb.setSelected(this.presentation.isAddSlotStateInterruptedStroke());

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    int screenHeight = screenSize.height * 3 / 16;
    int screenWidth = screenSize.width / 4;

    setSize(new Dimension(screenWidth, screenHeight));
    setLocationRelativeTo(MainFrame.getInstance());

    jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
    panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
    panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
  }

  private void addItems() {
    panel1.add(lineWidthLbl);
    panel1.add(lineWidthCmb);
    panel2.add(interruptedLbl);
    panel2.add(interruptedChb);

    jPanel.add(Box.createVerticalStrut(30));
    jPanel.add(panel1);
    jPanel.add(Box.createVerticalStrut(20));
    jPanel.add(panel2);

    add(jPanel);
  }

  private void addListeners() {
    interruptedChb.addItemListener(e -> {
      boolean isSelected = getPresentation().isAddSlotStateInterruptedStroke();
      if (isSelected) {
        getPresentation().setAddSlotStateInterruptedStroke(false);
      } else {
        getPresentation().setAddSlotStateInterruptedStroke(true);
      }
    });

    lineWidthCmb.addItemListener(e -> {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        getPresentation().setAddSlotStateLineWidth((Integer) e.getItem());
      }
    });
  }
}
