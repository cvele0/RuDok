package model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter

public abstract class RuNodeComposite extends RuNode {
  private List<RuNode> children = new ArrayList<>();

  public RuNodeComposite() {

  }

  public abstract void addChild(RuNode ruNode);
  public abstract void removeChild(RuNode ruNode);
}