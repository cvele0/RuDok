package view.rectangle;

import model.Slot;

public class SlideshowRectangleSlotView extends RectangleSlotView {
  public SlideshowRectangleSlotView(Slot slot) {
    super(slot);
    setX(slot.getPosition().x * 2);
    setY(slot.getPosition().y * 2);
    setWidth(slot.getDimension().width * 2);
    setHeight(slot.getDimension().height * 2);
    setLineWidth(slot.getLineWidth() * 2);
    setInterruptedScale(14.0f);

    setXStartDistance(15);
    setYStartDistance(30);
    setFontSize(18);
    setXDistance(10);
    setYDistance(20);
    setNumberOfLetters(8);
    setNumberOfRows(4);
  }
}
