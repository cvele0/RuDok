package view.popups;

import model.Slot;
import view.MainFrame;
import view.content.MultimediaSlotHandler;
import view.content.TextSlotHandler;
import view.rectangle.SlideRectangleSlotView;

import javax.swing.*;
import java.awt.*;

public class SelectSlotTypeDialog extends JDialog {
  private JButton imageBtn;
  private JButton textBtn;
  private SlideRectangleSlotView slotView;

  public SelectSlotTypeDialog(SlideRectangleSlotView slotView) {
    super(MainFrame.getInstance(), "Select slot type", true);
    this.slotView = slotView;
    initialize();
    addItems();
  }

  private void initialize() {
    imageBtn = new JButton("Image");
    textBtn = new JButton("Text");

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    int screenHeight = screenSize.height * 3 / 14;
    int screenWidth = screenSize.width / 4;

    setSize(new Dimension(screenWidth, screenHeight));
    setLocationRelativeTo(MainFrame.getInstance());
    setLayout(new GridLayout(2, 1, 10, 10));
  }

  private void addItems() {
    add(textBtn);
    add(imageBtn);

    textBtn.addActionListener(e -> {
      Slot slot = slotView.getSlot();
      slot.setType(Slot.Type.TEXT);
      slotView.setSlotHandler(new TextSlotHandler(slotView));
      setVisible(false);
    });

    imageBtn.addActionListener(e -> {
      Slot slot = slotView.getSlot();
      slot.setType(Slot.Type.IMAGE);
      slotView.setSlotHandler(new MultimediaSlotHandler(slotView));
      setVisible(false);
    });
  }
}
