package model.factory;

import model.RuNode;
import model.Slide;

public class SlideFactory extends AbstractNodeFactory {
  @Override
  public RuNode createRuNode() {
    return new Slide();
  }
}
