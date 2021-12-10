package controller.actions;

import error.ErrorFactory;
import gui.swing.tree.MyTreeNode;
import model.Presentation;
import model.Project;
import model.RuNode;
import model.Slide;
import view.MainFrame;

import javax.swing.*;
import java.net.URL;

public abstract class AbstractRudokAction extends AbstractAction {

  public Icon loadIcon(String fileName) {
    URL imageURL = getClass().getResource(fileName);
    Icon icon = null;

    if (imageURL != null) {
      icon = new ImageIcon(imageURL);
    } else {
      System.err.println("Resource not found " + fileName);
    }

    return icon;
  }

  public boolean checkForErrors(MyTreeNode myTreeNode) {
    if (myTreeNode == null) {
      ErrorFactory.getInstance().generateError(this, "Please select a node.");
      return true;
    }

    //Message that shows when trying to add a child after using remove-node function if no node is selected
    if (noLastSelectedNode(myTreeNode.getRuNode())) {
      ErrorFactory.getInstance().generateError(this, "Please select a node.");
      return true;
    }

    return false;
  }

  public boolean noLastSelectedNode(RuNode ruNode) {
    if (ruNode instanceof Slide) {
      ruNode = MainFrame.getInstance().getLastSelectedSlide();
    } else if (ruNode instanceof Presentation) {
      ruNode = MainFrame.getInstance().getLastSelectedPresentation();
    } else if (ruNode instanceof Project) {
      ruNode = MainFrame.getInstance().getLastSelectedProject();
    }
    return (ruNode == null);
  }
}
