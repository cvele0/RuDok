package model.factory;

import model.Project;
import model.RuNode;

public class ProjectFactory extends AbstractNodeFactory {
  @Override
  public RuNode createRuNode() {
    return new Project();
  }
}
