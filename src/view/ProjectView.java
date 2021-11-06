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

  private PresentationView presentationView;

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
    presentationView = new PresentationView();
    jTabbedPane = new JTabbedPane();

    jTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT); //JTabbedPane.WRAP_TAB_LAYOUT

    jLabel = new JLabel();
    jLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
    jLabel.setForeground(Color.BLACK);
    jLabel.setBorder(new EmptyBorder(0, 15, 0, 0));

    setBackground(Color.ORANGE);
    setMinimumSize(new Dimension(300, 300));

    setLayout(new BorderLayout());
  }

  private void addElements() {
    add(jLabel, BorderLayout.NORTH);
    add(jTabbedPane, BorderLayout.CENTER);
  }

  @Override
  public void update(Object notification) {
    /*if (notification == "ResetProject") {
      jLabel.setText("");
      jTabbedPane.removeAll();
      return;
    }*/

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
