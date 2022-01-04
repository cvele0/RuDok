package gui.swing.tree;

import lombok.Getter;
import lombok.Setter;
import model.RuNode;
import model.RuNodeComposite;
import view.MainFrame;

import javax.swing.*;
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

  private List<MyTreeNode> projects = new ArrayList<>();

  public MyTreeNode(RuNode ruNode) {
    this.ruNode = ruNode;
    parent = null;
  }

  public void setChanged(boolean changed) {
    ruNode.setChanged(changed);
    SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
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
    return parent;
  }

  @Override
  public int getIndex(TreeNode arg0) {
    return projects.indexOf((MyTreeNode) arg0);
  }

  @Override
  public Enumeration<TreeNode> children() {
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
    return ruNode instanceof RuNodeComposite;
  }

  @Override
  public boolean isLeaf() {
    return !(ruNode instanceof RuNodeComposite);
  }
}
