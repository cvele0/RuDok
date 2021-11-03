package model.workspace;

public class Workspace extends RuNodeComposite {
  public Workspace(RuNode parent, String name) {
    super(parent, name);
  }

  @Override
  public void addChild(RuNode ruNode) {
    if (ruNode instanceof Project) {
      getChildren().add(ruNode);
    } else {
      //TODO throw an error
    }
  }

  @Override
  public void removeChild(RuNode ruNode) {
    if (ruNode instanceof Project) {
      getChildren().remove(ruNode);
    } else {
      //TODO throw an error
    }
  }
}
