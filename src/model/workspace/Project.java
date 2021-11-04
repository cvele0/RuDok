package model.workspace;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Project extends RuNodeComposite {
  public static int projectCounter = 0;
  private int serialNumber;

  public Project(RuNode parent, String name) {
    super(parent, name);
  }

  @Override
  public String toString() {
    return getName() + " " + getSerialNumber();
  }

  @Override
  public void addChild(RuNode ruNode) {
    if (ruNode instanceof Presentation) {
      getChildren().add(ruNode);
    } else {
      //TODO throw an error
    }
  }

  @Override
  public void removeChild(RuNode ruNode) {
    if (ruNode instanceof Presentation) {
      getChildren().remove(ruNode);
    } else {
      //TODO throw an error
    }
  }
}
