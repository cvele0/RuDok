package view.content;

import view.rectangle.RectangleSlotView;

import java.awt.*;

public interface SlotHandler {
  void readContent();
  void setContent();
  void paint(RectangleSlotView rectangleSlotView, Graphics2D g);
  //void format();
}
