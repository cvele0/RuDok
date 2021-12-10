package model.factory;

import model.Project;
import model.RuNode;
import model.Workspace;

public class FactoryGenerator {
  static ProjectFactory projectFactory = new ProjectFactory();
  static PresentationFactory presentationFactory = new PresentationFactory();
  static SlideFactory slideFactory = new SlideFactory();

  public static AbstractNodeFactory returnFactory(RuNode ruNode) {
    if (ruNode instanceof Workspace) {
      return projectFactory;
    } else if (ruNode instanceof Project) {
      return presentationFactory;
    } else {
      return slideFactory;
    }
  }
}
