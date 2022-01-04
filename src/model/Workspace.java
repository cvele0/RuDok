package model;

import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter

public class Workspace extends RuNodeComposite {
  private File workspaceFile;

  public Workspace() {
    setParent(null);
    setName("Workspace");
  }

  @Override
  public String toString() {
    return ((isChanged() ? "*" : "") + getName());
  }

  @Override
  public void addChild(RuNode ruNode) {
    if (ruNode instanceof Project) {
      getChildren().add(ruNode);
    }
  }

  @Override
  public void removeChild(RuNode ruNode) {
    if (ruNode instanceof Project) {
      getChildren().remove(ruNode);
    }
  }
}