package view;

import lombok.Getter;
import lombok.Setter;
import model.workspace.Presentation;
import model.workspace.Project;
import model.workspace.RuNode;
import observer.ISubscriber;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.List;

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
  }

  public ProjectView(Project project) {
    this.project = project;
    if (this.project != null) {
      this.project.addSubscriber(this);
    }
    initialize();
    addElements();
  }

  public void setProject(Project project) {
    this.project = project;
    if (this.project != null) {
      this.project.addSubscriber(this);
    }
  }

  private void initialize() {
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
    if (notification == "ResetProject") {
      jLabel.setText("");
      jTabbedPane.removeAll();
      return;
    }

    if (this.project == null) {
      jLabel.setText("");
    } else {
      jLabel.setText(this.project.toString());
    }

    jTabbedPane.removeAll();
    for (RuNode item : project.getChildren()) {
      PresentationView presentationView = new PresentationView((Presentation) item);
      jTabbedPane.add(item.getName(), presentationView);
    }
  }
}
