package model.workspace;

import gui.swing.tree.MyTreeNode;
import lombok.Getter;
import lombok.Setter;
import view.MainFrame;

@Getter
@Setter

public class Project extends RuNodeComposite {
  public Project(RuNode parent, String name) {
    super(parent, name);
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
