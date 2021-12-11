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
  }
}
