package controller;

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

  public InfoAction getInfoAction() {
    return infoAction;
  }
  public NewProjectAction getNewProjectAction() {
    return newProjectAction;
  }
}
