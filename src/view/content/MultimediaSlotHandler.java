package view.content;

import view.rectangle.RectangleSlotView;

import java.awt.*;

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

  }
}
