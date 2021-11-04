package gui.swing.tree;

import model.workspace.*;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class WorkspaceTreeCellRenderer extends DefaultTreeCellRenderer {
  public WorkspaceTreeCellRenderer() {
    // TODO Auto-generated constructor stub
  }

  public Component getTreeCellRendererComponent(
          JTree tree,
          Object value,
          boolean sel,
          boolean expanded,
          boolean leaf,
          int row,
          boolean hasFocus) {
    super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

    if (value instanceof MyTreeNode) {
      MyTreeNode myTreeNode = (MyTreeNode) value;

      if (myTreeNode.getRuNode() instanceof Project) {
        URL imageURL = getClass().getResource("images/project20x20.png");
        Icon icon = null;
        if (imageURL != null) {
          icon = new ImageIcon(imageURL);
        }
        setIcon(icon);
      } else if (myTreeNode.getRuNode() instanceof Presentation) {
        URL imageURL = getClass().getResource("images/presentation25x25.png");
        Icon icon = null;
        if (imageURL != null) {
          icon = new ImageIcon(imageURL);
        }
        setIcon(icon);
      } else if (myTreeNode.getRuNode() instanceof Slide) {
        URL imageURL = getClass().getResource("images/slide20x20.png");
        Icon icon = null;
        if (imageURL != null) {
          icon = new ImageIcon(imageURL);
        }
        setIcon(icon);
      }
    }

    return this;
  }
}