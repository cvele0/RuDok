package controller;

import lombok.Getter;

@Getter

public class ActionManager {
  private InfoAction infoAction;
  private NewProjectAction newProjectAction;
  private EditInfoAction editInfoAction;
  private RemoveProjectAction removeProjectAction;

  public ActionManager() {
    initialize();
  }

  private void initialize() {
    infoAction = new InfoAction();
    newProjectAction = new NewProjectAction();
    editInfoAction = new EditInfoAction();
    removeProjectAction = new RemoveProjectAction();
  }
}
