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
  }
}
