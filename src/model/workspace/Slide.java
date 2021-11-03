package model.workspace;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Slide extends RuNode {
  private int pageNumber;

  public Slide(RuNode parent, String name) {
    super(parent, name);
  }
}
