package view;

import lombok.Getter;
import lombok.Setter;
import model.workspace.Project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

@Getter
@Setter

public class ProjectView extends JPanel {
  private Project project;

  private TextArea textArea;
  private PresentationView presentationView;

  public ProjectView(Project project) {
    this.project = project;
    initialize();
    addElements();
  }

  public ProjectView() {}

  private void initialize() {
    presentationView = new PresentationView();
    textArea = new TextArea();

    setBackground(Color.LIGHT_GRAY);
    setMinimumSize(new Dimension(300, 300));

    BoxLayout boxLayout = new BoxLayout()
    setLayout(new BorderLayout());
  }

  private void addElements() {
    JPanel textPanel = new JPanel();
    BoxLayout boxLayout = new BoxLayout(textPanel, BoxLayout.X_AXIS);

    textArea.setText(project.toString());
    textArea.setEditable(false);
    textArea.setFont(new Font("Times New Roman", Font.BOLD, 22));

    textPanel.add(textArea);
    textPanel.setBorder(new EmptyBorder(25, 10, 10, 10));

    add(textPanel, BorderLayout.NORTH);
    add(presentationView, BorderLayout.CENTER);
  }
}
