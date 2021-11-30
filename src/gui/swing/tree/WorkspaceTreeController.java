package gui.swing.tree;

import model.workspace.Presentation;
import model.workspace.Project;
import model.workspace.RuNode;
import model.workspace.Slide;
import view.MainFrame;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class WorkspaceTreeController implements TreeSelectionListener {
  @Override
  public void valueChanged(TreeSelectionEvent e) {
    TreePath path = e.getPath();

    for (int i = 0; i < path.getPathCount(); i++) {
      MyTreeNode myTreeNode = (MyTreeNode) path.getPathComponent(i);
      RuNode ruNode = myTreeNode.getRuNode();

      if (ruNode instanceof Project) {
        MainFrame.getInstance().setLastSelectedProject((Project) ruNode);
        MainFrame.getInstance().getWorkspaceTree().expandPath(path);
      } else if (ruNode instanceof Presentation) {
        MainFrame.getInstance().setLastSelectedPresentation((Presentation) ruNode);
        MainFrame.getInstance().getWorkspaceTree().expandPath(path);
      } else if (ruNode instanceof Slide) {
        MainFrame.getInstance().setLastSelectedSlide((Slide) ruNode);
        MainFrame.getInstance().getWorkspaceTree().expandPath(path);
      }
    }
  }
}
