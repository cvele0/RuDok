package view.rectangle;

import model.Slot;

public class SlideRectangleSlotView extends RectangleSlotView {
  public SlideRectangleSlotView(Slot slot) {
    super(slot);
    setX(slot.getPosition().x);
    setY(slot.getPosition().y);
    setWidth(slot.getDimension().width);
    setHeight(slot.getDimension().height);
    setLineWidth(slot.getLineWidth());
    setInterruptedScale(7.0f);

    setXStartDistance(15 / 2);
    setYStartDistance(30 / 2);
    setFontSize(18 / 2);
    setXDistance(10 / 2);
    setYDistance(20 / 2);
    setNumberOfLetters(8);
    setNumberOfRows(4);
  }
}
