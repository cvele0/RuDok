package state.slideshow;

import lombok.Getter;

@Getter

public class StateManager {
  private State currentState;
  private SlideshowState slideshowState;
  private EditState editState;

  public StateManager() {
    initialize();
  }

  private void initialize() {
    slideshowState = new SlideshowState();
    editState = new EditState();
    this.currentState = editState;
  }

  public void setEditState() {
    this.currentState = editState;
  }

  public void setSlideshowState() {
    this.currentState = slideshowState;
  }
}
