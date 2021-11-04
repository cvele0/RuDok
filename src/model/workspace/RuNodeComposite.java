package model.workspace;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter

public abstract class RuNodeComposite extends RuNode {
  private List<RuNode> children = new ArrayList<RuNode>();

  public RuNodeComposite(RuNode parent, String name) {
    super(parent, name);
  }

  public RuNodeComposite(RuNode parent) {
    super(parent);
  }

  public abstract void addChild(RuNode ruNode);
  public abstract void removeChild(RuNode ruNode);
}
