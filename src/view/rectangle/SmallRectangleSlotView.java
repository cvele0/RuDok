package view.rectangle;

import model.Slot;

public class SmallRectangleSlotView extends RectangleSlotView {
  public SmallRectangleSlotView(Slot slot) {
    super(slot);
    setX(slot.getPosition().x / 4.0);
    setY(slot.getPosition().y / 4.0);
    setWidth(slot.getDimension().width / 4.0);
    setHeight(slot.getDimension().height / 4.0);
    setLineWidth(slot.getLineWidth() / 4.0f);
    setInterruptedScale(7f / 4.0f);

    setXStartDistance(15 / 6);
    setYStartDistance(30 / 6);
    setFontSize(18 / 6);
    setXDistance(10 / 6);
    setYDistance(20 / 6);
    setNumberOfLetters(8);
    setNumberOfRows(4);
  }
}
