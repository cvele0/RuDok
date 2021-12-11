package view.rectangle;

import lombok.Getter;
import lombok.Setter;
import model.Slot;

import java.awt.*;
import java.awt.geom.GeneralPath;

@Getter
@Setter

public class RectangleSlotView {
  private Slot slot;
  private Shape shape;
  private double x;
  private double y;
  private double width;
  private double height;
  private float lineWidth;
  private float interruptedScale;

  public RectangleSlotView(Slot slot) {
    this.slot = slot;
    shape = new GeneralPath();
  }

  public void paint(Graphics graphics) {
    Graphics2D g = (Graphics2D) graphics;

    ((GeneralPath) shape).moveTo(x, y);
    ((GeneralPath) shape).lineTo(x + height , y);
    ((GeneralPath) shape).lineTo(x + width, y + height);
    ((GeneralPath) shape).lineTo(x, y + height);
    ((GeneralPath) shape).closePath();

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
