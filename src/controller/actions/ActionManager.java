package controller.actions;

import lombok.Getter;

@Getter

public class ActionManager {
  private InfoAction infoAction;
  private NewProjectAction newProjectAction;
  private EditInfoAction editInfoAction;
  private RemoveProjectAction removeProjectAction;
  private UndoAction undoAction;
  private RedoAction redoAction;

  public ActionManager() {
    initialize();
  }

  private void initialize() {
    infoAction = new InfoAction();
    newProjectAction = new NewProjectAction();
    editInfoAction = new EditInfoAction();
    removeProjectAction = new RemoveProjectAction();
    undoAction = new UndoAction();
    redoAction = new RedoAction();
    this.redoAction.setEnabled(false);
  }
}
