package view;

import lombok.Getter;
import model.workspace.Slot;

import java.awt.*;
import java.awt.geom.GeneralPath;

@Getter

public class RectangleSlotView {
  private Slot slot;
  private Shape shape;

  public RectangleSlotView(Slot slot) {
    this.slot = slot;
    shape = new GeneralPath();
  }

  public void paint(Graphics graphics) {
    Graphics2D g = (Graphics2D) graphics;

    ((GeneralPath) shape).moveTo(slot.getPosition().x, slot.getPosition().y);
    ((GeneralPath) shape).lineTo(slot.getPosition().x + slot.getDimension().width, slot.getPosition().y);
    ((GeneralPath) shape).lineTo(slot.getPosition().x + slot.getDimension().width, slot.getPosition().y + slot.getDimension().height);
    ((GeneralPath) shape).lineTo(slot.getPosition().x, slot.getPosition().y + slot.getDimension().height);
    ((GeneralPath) shape).closePath();

    g.setPaint(slot.getColor());
    g.setStroke(slot.getStroke());
    g.draw(getShape());

    g.setPaint(slot.getPaint());
    //g.fill(getShape());

    //g.drawRect(slot.getPosition().x, slot.getPosition().y, slot.getDimension().width, slot.getDimension().height);
  }

  public boolean elementAt(Point position) {
    return getShape().contains(position);
  }
}
