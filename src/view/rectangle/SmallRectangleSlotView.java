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
  }
}
