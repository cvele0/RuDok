package controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ActionManager {
  private InfoAction infoAction;
  private NewProjectAction newProjectAction;

  public ActionManager() {
    initialize();
  }

  private void initialize() {
    infoAction = new InfoAction();
    newProjectAction = new NewProjectAction();
  }
}
