package controller.actions;

import lombok.Getter;

@Getter

public class ActionManager {
  private InfoAction infoAction;
  private NewProjectAction newProjectAction;
  private EditInfoAction editInfoAction;
  private RemoveProjectAction removeProjectAction;
  private SlideshowAction slideshowAction;
  private ExitSlideshowAction exitSlideshowAction;

  public ActionManager() {
    initialize();
  }

  private void initialize() {
    infoAction = new InfoAction();
    newProjectAction = new NewProjectAction();
    editInfoAction = new EditInfoAction();
    removeProjectAction = new RemoveProjectAction();
    slideshowAction = new SlideshowAction();
    exitSlideshowAction = new ExitSlideshowAction();
  }
}
