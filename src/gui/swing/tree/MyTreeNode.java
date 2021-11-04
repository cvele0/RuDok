package gui.swing.tree;

import lombok.Getter;
import lombok.Setter;
import model.workspace.RuNode;
import model.workspace.RuNodeComposite;
import view.ProjectView;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Setter
@Getter

public class MyTreeNode extends DefaultMutableTreeNode {
  private RuNode ruNode;
  private MyTreeNode parent;
  private ProjectView projectView = null;

  private List<MyTreeNode> projects = new ArrayList<>();

  public MyTreeNode(RuNode ruNode) {
    this.ruNode = ruNode;
    parent = null;
  }

  @Override
  public String toString() {
    return getRuNode().toString();
  }

  @Override
  public MyTreeNode getChildAt(int arg0) {
    return projects.get(arg0);
  }

  @Override
  public int getChildCount() {
    return projects.size();
  }

  @Override
  public TreeNode getParent() {
    // TODO Auto-generated method stub
    return parent;
  }

  @Override
  public int getIndex(TreeNode arg0) {
    return projects.indexOf((MyTreeNode) arg0);
  }

  @Override
  public Enumeration<TreeNode> children() {
    // TODO Auto-generated method stub
    return (Enumeration<TreeNode>) projects;
  }

  public void addProject(MyTreeNode myTreeNode) {
    projects.add(myTreeNode);
  }

  public void removeProject(MyTreeNode myTreeNode) {
    projects.remove(myTreeNode);
  }

  @Override
  public boolean getAllowsChildren() {
    if (ruNode instanceof RuNodeComposite) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean isLeaf() {
    if (ruNode instanceof RuNodeComposite) {
      return false;
    } else {
      return true;
    }
  }
}
