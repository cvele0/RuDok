package view.rectangle;

import lombok.Getter;
import lombok.Setter;
import model.Slot;
import view.content.MultimediaSlotHandler;
import view.content.SlotHandler;
import view.content.TextSlotHandler;

import java.awt.*;
import java.awt.geom.GeneralPath;

@Getter
@Setter

public abstract class RectangleSlotView {
  private Slot slot;
  private Shape shape;
  private double x;
  private double y;
  private double width;
  private double height;
  private float lineWidth;
  private float interruptedScale;

  private int xStartDistance;
  private int yStartDistance;
  private int fontSize;
  private int xDistance;
  private int yDistance;
  private int numberOfLetters;
  private int numberOfRows;

  private SlotHandler slotHandler;

  public RectangleSlotView(Slot slot) {
    this.slot = slot;
    shape = new GeneralPath();
    if (slot.getType() == Slot.Type.TEXT) {
      setSlotHandler(new TextSlotHandler(this));
    } else {
      setSlotHandler(new MultimediaSlotHandler(this));
    }
  }

  public void paint(Graphics graphics) {
    Graphics2D g = (Graphics2D) graphics;

    ((GeneralPath) shape).moveTo(x, y);
    ((GeneralPath) shape).lineTo(x + height , y);
    ((GeneralPath) shape).lineTo(x + width, y + height);
    ((GeneralPath) shape).lineTo(x, y + height);
    ((GeneralPath) shape).closePath();

    slotHandler.paint(this, g);

    g.setPaint(slot.getColor());
    if (!slot.isInterruptedStroke()) {
      g.setStroke(new BasicStroke(lineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
    } else {
      g.setStroke(new BasicStroke(lineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
              2f, new float[]{interruptedScale}, 0.0f));
    }
    g.draw(getShape());

    //g.setPaint(slot.getPaint());
    //g.fill(getShape());
  }

  public boolean elementAt(Point position) {
    return getShape().contains(position);
  }
}
