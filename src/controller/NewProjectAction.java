package controller;

import gui.swing.tree.MyTreeNode;
import gui.swing.tree.WorkspaceModel;
import model.workspace.*;
import view.MainFrame;
import view.ProjectView;

import javax.swing.*;
import javax.swing.tree.TreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class NewProjectAction extends AbstractRudokAction {
  public NewProjectAction() {
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
    putValue(SMALL_ICON, loadIcon("images/new25x25.png"));
    putValue(NAME, "New Project");
    putValue(SHORT_DESCRIPTION, "New Project");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    MyTreeNode myTreeNode = (MyTreeNode) MainFrame.getInstance().getWorkspaceTree().getLastSelectedPathComponent();

    if (myTreeNode == null) return;

    RuNode parent = myTreeNode.getRuNode();
    int size = myTreeNode.getChildCount();

    if (parent instanceof Workspace) {
      Project project = new Project(parent, "Project");
      project.setSerialNumber(++Project.projectCounter);
      MyTreeNode newNode = new MyTreeNode(project);

      ProjectView projectView = new ProjectView(project);
      MainFrame.getInstance().addProjectView(projectView);
      newNode.setProjectView(projectView);

      ((Workspace) parent).addChild(project);

      newNode.setParent(myTreeNode);
      MainFrame.getInstance().getWorkspaceTree().addProject(newNode);
    } else if (parent instanceof Project) {
      RuNode presentation = new Presentation(parent, "Presentation " + (size + 1));
      MyTreeNode node = new MyTreeNode(presentation);

      ((Project) parent).addChild(presentation);

      node.setParent(myTreeNode);
      MainFrame.getInstance().getWorkspaceTree().addProject(node);
    } else if (parent instanceof Presentation) {
      RuNode slide = new Slide(parent, "Slide " + (size + 1));
      MyTreeNode node = new MyTreeNode(slide);

      ((Presentation) parent).addChild(slide);

      node.setParent(myTreeNode);
      MainFrame.getInstance().getWorkspaceTree().addProject(node);
    }
  }
}
