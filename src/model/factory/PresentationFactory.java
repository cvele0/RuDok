package model.factory;

import model.Presentation;
import model.RuNode;

public class PresentationFactory extends AbstractNodeFactory {
  @Override
  public RuNode createRuNode() {
    return new Presentation();
  }
}
