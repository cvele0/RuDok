package view;

import lombok.Getter;
import lombok.Setter;
import model.project.ProjectModel;

import javax.swing.*;

@Getter
@Setter

public class ProjectView extends JPanel {
  private ProjectModel projectModel;

  public ProjectView() {

  }
}
