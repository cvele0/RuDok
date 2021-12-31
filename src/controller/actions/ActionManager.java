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
  private SaveContentAction saveContentAction;
  private BoldAction boldAction;
  private ItalicAction italicAction;
  private UnderlineAction underlineAction;
  private OpenFileAction openFileAction;

  public ActionManager() {
    initialize();
  }

  private void initialize() {
    infoAction = new InfoAction();
    newProjectAction = new NewProjectAction();
    editInfoAction = new EditInfoAction();
    removeProjectAction = new RemoveProjectAction();
    undoAction = new UndoAction();
    redoAction = new RedoAction(); this.redoAction.setEnabled(false);
    saveContentAction = new SaveContentAction();
    boldAction = new BoldAction();
    italicAction = new ItalicAction();
    underlineAction = new UnderlineAction();
    openFileAction= new OpenFileAction();
  }
}
