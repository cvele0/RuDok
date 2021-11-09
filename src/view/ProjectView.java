package view;

import lombok.Getter;
import lombok.Setter;
import model.workspace.Presentation;
import model.workspace.Project;
import model.workspace.RuNode;
import observer.ISubscriber;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

@Getter
@Setter

public class ProjectView extends JPanel implements ISubscriber {
  private Project project;

  private JLabel jLabel;

  private JTabbedPane jTabbedPane;

  public ProjectView() {
    this.project = null;
    initialize();
    addElements();
    update(this);
  }

  public ProjectView(Project project) {
    this.project = project;
    if (this.project != null) {
      this.project.addSubscriber(this);
    }
    initialize();
    addElements();
    update(this);
  }

  public void setProject(Project project) {
    if (this.project != null) {
      this.project.getSubscribers().remove(this);
    }
    this.project = project;
    if (this.project != null) {
      this.project.addSubscriber(this);
    }
    update(this);
  }

  private void initialize() {
    jTabbedPane = new JTabbedPane();

    jTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT); //JTabbedPane.WRAP_TAB_LAYOUT

    jLabel = new JLabel();
    jLabel.setPreferredSize(new Dimension(20, 45));
    jLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
    jLabel.setForeground(Color.BLACK);
    //jLabel.setForeground(Color.WHITE);
    jLabel.setBorder(new EmptyBorder(5, 15, 5, 0));

    setBackground(new Color(252, 47, 44));
    //setBackground(new Color(67, 146, 206));
    //setBackground(new Color(238, 173, 33));
    setMinimumSize(new Dimension(300, 300));

    setLayout(new BorderLayout());
  }

  private void addElements() {
    add(jLabel, BorderLayout.NORTH);
    add(jTabbedPane, BorderLayout.CENTER);
  }

  @Override
  public void update(Object notification) {
    if (this.project == null) {
      jLabel.setText("");
    } else {
      jLabel.setText(this.project.toString());
    }

    jTabbedPane.removeAll();
    if (this.project != null) {
      for (RuNode item : this.project.getChildren()) {
        PresentationView presentationView = new PresentationView((Presentation) item);
        jTabbedPane.addTab(item.getName(), presentationView);
      }
    }
    Presentation lastSelected = MainFrame.getInstance().getLastSelectedPresentation();
    if (lastSelected != null) {
      int indexOfSelected = ((Project) lastSelected.getParent()).getChildren().indexOf(lastSelected);
      MainFrame.getInstance().getProjectView().getJTabbedPane().setSelectedIndex(indexOfSelected);
    }
  }
}