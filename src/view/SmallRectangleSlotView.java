package view;

import lombok.Getter;
import model.Slot;

import java.awt.*;
import java.awt.geom.GeneralPath;

@Getter

public class SmallRectangleSlotView {
  private Slot slot;
  private Shape shape;

  public SmallRectangleSlotView(Slot slot) {
    this.slot = slot;
    shape = new GeneralPath();
  }

  public void paint(Graphics graphics) {
    Graphics2D g = (Graphics2D) graphics;

    double x = slot.getPosition().x / 4.0;
    double y = slot.getPosition().y / 4.0;

    double width = getSlot().getDimension().width / 4.0;
    double height = getSlot().getDimension().height / 4.0;

    ((GeneralPath) shape).moveTo(x, y);
    ((GeneralPath) shape).lineTo(x + height , y);
    ((GeneralPath) shape).lineTo(x + width, y + height);
    ((GeneralPath) shape).lineTo(x, y + height);
    ((GeneralPath) shape).closePath();

    g.setPaint(slot.getColor());
    float size = (float) (slot.getLineWidth() / 4.0);
    if (!slot.isInterruptedStroke()) {
      g.setStroke(new BasicStroke(size, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
    } else {
      g.setStroke(new BasicStroke(size, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
              2f, new float[]{7f / 4.0f}, 0.0f));
    }
    g.draw(getShape());

    //g.setPaint(slot.getPaint());
    //g.fill(getShape());
    //g.drawRect(slot.getPosition().x, slot.getPosition().y, slot.getDimension().width, slot.getDimension().height);
  }

  public boolean elementAt(Point position) {
    return getShape().contains(position);
  }
}
