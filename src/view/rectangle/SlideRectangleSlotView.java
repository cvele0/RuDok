package view.rectangle;

import model.Slot;
import view.content.MultimediaSlotHandler;
import view.content.TextSlotHandler;

public class SlideRectangleSlotView extends RectangleSlotView {
  public SlideRectangleSlotView(Slot slot) {
    super(slot);
    if (slot.getType() == Slot.Type.TEXT) {
      setSlotHandler(new TextSlotHandler(this));
    } else {
      setSlotHandler(new MultimediaSlotHandler(this));
    }
    setX(slot.getPosition().x);
    setY(slot.getPosition().y);
    setWidth(slot.getDimension().width);
    setHeight(slot.getDimension().height);
    setLineWidth(slot.getLineWidth());
    setInterruptedScale(7.0f);
  }
}
