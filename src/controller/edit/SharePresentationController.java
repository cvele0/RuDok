package controller.edit;

import error.ErrorFactory;
import gui.swing.tree.MyTreeNode;
import model.Presentation;
import model.Project;
import model.RuNode;
import view.MainFrame;
import view.popups.SharePresentationDialog;

import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SharePresentationController implements ActionListener {
  private SharePresentationDialog sharePresentationDialog;

  public SharePresentationController(SharePresentationDialog sharePresentationDialog) {
    this.sharePresentationDialog = sharePresentationDialog;
  }

  private MyTreeNode findMyNode(Project project) {
    MyTreeNode workspace = (MyTreeNode) MainFrame.getInstance().getWorkspaceModel().getRoot();
    for (MyTreeNode myTreeNode : workspace.getProjects()) {
      if (myTreeNode.getRuNode() == project) {
        return myTreeNode;
      }
    }
    return null;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Project selectedProject = sharePresentationDialog.getSelectedProject();
    Presentation presentation = sharePresentationDialog.getPresentation();

    if (selectedProject == null) return;
    sharePresentationDialog.setVisible(false);
    MyTreeNode projectNode = findMyNode(selectedProject);

    if (projectNode == null) {
      ErrorFactory.getInstance().generateError(this, "Error while sharing a presentation.");
      return;
    }

    //Add presentation node
    MyTreeNode newNode = new MyTreeNode(presentation);
    newNode.setParent(projectNode);
    selectedProject.addChild(presentation); //added
    selectedProject.setChanged(true);
    presentation.setShared(true);
    MainFrame.getInstance().getWorkspaceTree().addProject(newNode);
    MainFrame.getInstance().getWorkspaceTree().expandPath(new TreePath(newNode.getPath()));

    //Add slide nodes
    for (RuNode ruNode : presentation.getChildren()) {
      MyTreeNode newSlide = new MyTreeNode(ruNode);
      newSlide.setParent(newNode);
      MainFrame.getInstance().getWorkspaceTree().addProject(newSlide);
    }
  }
}
