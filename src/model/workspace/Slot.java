package model.workspace;

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

}
