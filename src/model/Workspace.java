package model;

public class Workspace extends RuNodeComposite {
  public Workspace() {
    setParent(null);
    setName("Workspace");
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