package view.popups;

import controller.edit.SharePresentationController;
import gui.swing.tree.MyTreeNode;
import lombok.Getter;
import model.Presentation;
import model.Project;
import model.RuNode;
import model.Workspace;
import view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

@Getter

public class SharePresentationDialog extends JDialog {
  private Presentation presentation;
  private Project selectedProject;

  private JPanel jPanel;
  private JPanel panel1;
  private JPanel panel2;

  private JLabel selectProjectLbl;

  private JComboBox<Project> selectProjectCmb;
  private JButton shareBtn;

  public SharePresentationDialog(Presentation presentation) {
    super(MainFrame.getInstance(), "Share presentation", true);
    this.presentation = presentation;
    initialize();
    addItems();
    addListeners();
  }

  private void initialize() {
    jPanel = new JPanel();
    panel1 = new JPanel();
    panel2 = new JPanel();

    panel1.setPreferredSize(new Dimension(200, 20));
    panel1.setMaximumSize(new Dimension(200, 20));

    selectProjectLbl = new JLabel("Share in:  ");
    selectProjectCmb = new JComboBox<>();
    Workspace workspace = (Workspace) ((MyTreeNode) MainFrame.getInstance().getWorkspaceModel().getRoot()).getRuNode();
    for (RuNode ruNode : workspace.getChildren()) {
      selectProjectCmb.addItem((Project) ruNode);
    }
    selectProjectCmb.setSelectedIndex(-1);

    shareBtn = new JButton("Share");

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    int screenHeight = screenSize.height * 3 / 16;
    int screenWidth = screenSize.width / 4;

    setSize(new Dimension(screenWidth, screenHeight));
    setLocationRelativeTo(MainFrame.getInstance());

    jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
    panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
    panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
  }

  private void addItems() {
    panel1.add(selectProjectLbl);
    panel1.add(selectProjectCmb);
    panel2.add(shareBtn);

    jPanel.add(Box.createVerticalStrut(35));
    jPanel.add(panel1);
    jPanel.add(Box.createVerticalStrut(25));
    jPanel.add(panel2);

    add(jPanel);
  }

  private void addListeners() {
    selectProjectCmb.addItemListener(e -> {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        this.selectedProject = (Project) e.getItem();
      }
    });

    shareBtn.addActionListener(new SharePresentationController(this));
  }
}
