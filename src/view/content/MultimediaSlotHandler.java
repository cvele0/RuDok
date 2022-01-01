package view.content;

import view.rectangle.RectangleSlotView;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class MultimediaSlotHandler implements SlotHandler {
  private RectangleSlotView slotView;
  private MultimediaEditor multimediaEditor;

  public MultimediaSlotHandler(RectangleSlotView slotView) {
    this.slotView = slotView;
    this.multimediaEditor = null;
  }

  @Override
  public void readContent() {
    this.multimediaEditor = new MultimediaEditor(this.slotView);
    this.multimediaEditor.setVisible(true);
  }

  @Override
  public void setContent() {
    slotView.getSlot().setSlotContent(this.multimediaEditor.getLastSelectedFilePath());
  }

  @Override
  public void paint(RectangleSlotView rectangleSlotView, Graphics2D g) {
    URL url = null;
    try {
      if (this.slotView.getSlot().getSlotContent() != null) {
        url = new File(this.slotView.getSlot().getSlotContent()).toURI().toURL();
      }
    } catch (MalformedURLException e) {
      System.err.println("Invalid resource.");
    }
    if (url == null) return;
    Image img = new ImageIcon(url).getImage();
    g.drawImage(img, (int) rectangleSlotView.getX(), (int) rectangleSlotView.getY(),
            (int) rectangleSlotView.getWidth(), (int) rectangleSlotView.getHeight(), null);
  }
}
