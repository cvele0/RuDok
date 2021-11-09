package model.workspace;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RuNode {
  private String name;
  private RuNode parent;
  public RuNode(RuNode parent) {
    this.parent = parent;
  }

  public RuNode(RuNode parent, String name) {
    this.parent = parent;
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
