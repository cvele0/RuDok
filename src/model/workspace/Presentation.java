package model.workspace;

import lombok.Getter;
import lombok.Setter;
import state.slideshow.StateManager;
import state.slot.SlotStateManager;
import view.SlideView;

import java.awt.*;
import java.net.URL;

@Getter
@Setter

public class Presentation extends RuNodeComposite {
  private String authorName;
  private URL imageURL;

  private SlotStateManager slotStateManager;
  private StateManager stateManager;

  public Presentation(RuNode parent, String name) {
    super(parent, name);
    slotStateManager = new SlotStateManager();
    stateManager = new StateManager();
    this.authorName = "Unknown";
    this.imageURL = getClass().getResource("images/default.jpg");
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
    notifySubscribers(this);
  }

  public void setImageURL(URL imageURL) {
    this.imageURL = imageURL;
    notifySubscribers(this);
  }

  public void startSelectSlotState() {
    this.slotStateManager.setSelectSlotState();
  }

  public void startAddSlotState() {
    this.slotStateManager.setAddSlotState();
  }

  public void startRemoveSlotState() {
    this.slotStateManager.setRemoveSlotState();
  }

  public void startMoveSlotState() {
    this.slotStateManager.setMoveSlotState();
  }

  public void startMouseClick(Slide slide, Slot slot, Point position) {
    this.slotStateManager.getCurrentSlotState().mouseClick(slide, slot, position);
  }

  public void startEditState() {
    this.stateManager.setEditState();
  }

  public void startSlideshowState() {
    this.stateManager.setSlideshowState();
  }

  public void setAddSlotStateColor(Color color) {
    this.slotStateManager.getAddSlotState().setColor(color);
  }

  @Override
  public void addChild(RuNode ruNode) {
    if (ruNode instanceof Slide) {
      getChildren().add(ruNode);
      notifySubscribers(this);
    }
  }

  @Override
  public void removeChild(RuNode ruNode) {
    if (ruNode instanceof Slide) {
      getChildren().remove(ruNode);
      notifySubscribers(this);
    }
  }
}