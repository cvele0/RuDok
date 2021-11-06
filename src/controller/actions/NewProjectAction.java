package controller.actions;

import gui.swing.tree.MyTreeNode;
import model.workspace.*;
import view.MainFrame;

import javax.swing.*;
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
      Project project = new Project(parent);
      project.setSerialNumber(++Project.projectCounter);
      project.setName("Project " + project.getSerialNumber());

      ((Workspace) parent).addChild(project);

      MyTreeNode newNode = new MyTreeNode(project);
      newNode.setParent(myTreeNode);

      MainFrame.getInstance().getWorkspaceTree().addProject(newNode);
    }
    else if (parent instanceof Project) {
      Presentation presentation = new Presentation(parent, "Presentation " + (size + 1));
      ((Project) parent).addChild(presentation);

      MyTreeNode node = new MyTreeNode(presentation);
      node.setParent(myTreeNode);

      MainFrame.getInstance().getWorkspaceTree().addProject(node);
    }
    else if (parent instanceof Presentation) {
      RuNode slide = new Slide(parent, "Slide " + (size + 1));
      ((Presentation) parent).addChild(slide);

      MyTreeNode node = new MyTreeNode(slide);
      node.setParent(myTreeNode);
      MainFrame.getInstance().getWorkspaceTree().addProject(node);

      MainFrame.getInstance().refresh();

      //int indexOfSelected = ((Project) parent.getParent()).getChildren().indexOf(parent);
      //MainFrame.getInstance().getProjectView().getJTabbedPane().setSelectedIndex(indexOfSelected);
    }
  }
}
