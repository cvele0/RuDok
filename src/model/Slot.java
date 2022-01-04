package model;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.io.Serializable;

@Getter
@Setter

public class Slot implements Serializable {
  private Point position;
  private Dimension dimension;
  //private Stroke stroke;
  private Color color;
  private int lineWidth;
  private boolean interruptedStroke;
  private Type type;
  private String slotContent;

  private Slide parent;

  public Slot(Point position) {
    this.position = position;
    setDimension(new Dimension(50, 50));
  }

  public enum Type {
    TEXT,
    IMAGE
  }
}
