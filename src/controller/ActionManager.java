package controller;

import lombok.Getter;

@Getter

public class ActionManager {
  private InfoAction infoAction;
  private NewProjectAction newProjectAction;
  private EditInfoAction editInfoAction;

  public ActionManager() {
    initialize();
  }

  private void initialize() {
    infoAction = new InfoAction();
    newProjectAction = new NewProjectAction();
    editInfoAction = new EditInfoAction();
  }
}
