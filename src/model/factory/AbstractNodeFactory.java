package model.factory;

import model.RuNode;
import model.RuNodeComposite;

public abstract class AbstractNodeFactory {
  public RuNode getNodeForTree(RuNode parent, int size) {
    RuNode newNode = createRuNode();
    newNode.setParent(parent);
    newNode.setName("", size);
    ((RuNodeComposite) parent).addChild(newNode);
    return newNode;
  }

  public abstract RuNode createRuNode();
}
