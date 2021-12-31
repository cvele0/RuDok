package model;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter

public class Slot {
  private Point position;
  private Dimension dimension;
  private Stroke stroke;
  private Paint paint;
  private Color color;
  private int lineWidth;
  private boolean interruptedStroke;
  private Type type;
  private String slotContent;

  private Slide parent;

  public Slot(Point position) {
    this.position = position;
  }

  public enum Type {
    TEXT,
    IMAGE
  }
}
