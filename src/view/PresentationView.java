package view;

import lombok.Getter;
import lombok.Setter;
import model.workspace.Presentation;

import javax.swing.*;

@Getter
@Setter

public class PresentationView extends JTabbedPane {
  private Presentation presentation;

  public PresentationView() {
    initialize();
    addElements();
  }

  private void initialize() {
    setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT); //JTabbedPane.WRAP_TAB_LAYOUT

  }

  private void addElements() {
  }
}
