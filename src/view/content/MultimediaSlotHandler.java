package view.content;

import view.rectangle.SlideRectangleSlotView;

public class MultimediaSlotHandler implements SlotHandler {
  private SlideRectangleSlotView slotView;
  private MultimediaEditor multimediaEditor;

  public MultimediaSlotHandler(SlideRectangleSlotView slotView) {
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
  public void paint() {

  }
}
