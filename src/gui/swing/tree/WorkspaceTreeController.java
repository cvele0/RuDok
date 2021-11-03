package gui.swing.tree;

import model.workspace.Presentation;
import model.workspace.Project;
import model.workspace.RuNode;
import model.workspace.Slide;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class WorkspaceTreeController implements TreeSelectionListener {
  @Override
  public void valueChanged(TreeSelectionEvent e) {
    TreePath path = e.getPath();

    for (int i = 0; i < path.getPathCount(); i++) {
      MyTreeNode myTreeNode = (MyTreeNode) path.getPathComponent(i);
      System.out.println("Selektovan dijagram:" + myTreeNode.getRuNode());

      System.out.println("getPath: " + e.getPath());
      System.out.println("getPath: " + e.getNewLeadSelectionPath());
      System.out.println("getName: " + myTreeNode.getRuNode().getName());
      /*if (path.getPathComponent(i) instanceof Project) {
        Project d = (Project) path.getPathComponent(i);

        //selektovan je dijagram u stablu, potreno je pronaci odgovarajuci
        //DiagramView i postaviti ga u fokus
        System.out.println("Selektovan dijagram:" + d);
        System.out.println("getPath: " + e.getPath());
        System.out.println("getPath: " + e.getNewLeadSelectionPath());
        break;
      } */
    }
  }
}
