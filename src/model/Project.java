package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Project extends RuNodeComposite {
  public static int projectCounter = 0;
  private int serialNumber;

  public Project() {

  }

  @Override
  public void setName(String name, int size) {
    super.setName("Project " + (++Project.projectCounter));
  }

  @Override
  public String toString() {
    return getName();
  }

  @Override
  public void addChild(RuNode ruNode) {
    if (ruNode instanceof Presentation) {
      getChildren().add(ruNode);
      notifySubscribers(this);
    }
  }

  @Override
  public void removeChild(RuNode ruNode) {
    if (ruNode instanceof Presentation) {
      getChildren().remove(ruNode);
      notifySubscribers(this);
    }
  }
}