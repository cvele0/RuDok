package model;

import lombok.Getter;
import lombok.Setter;
import state.slideshow.State;
import state.slideshow.StateManager;
import state.slot.SlotState;
import state.slot.SlotStateManager;

import java.awt.*;
import java.net.URL;

@Getter
@Setter

public class Presentation extends RuNodeComposite {
  private String authorName;
  private URL imageURL;

  private int lastSelectedSlideView;

  private SlotStateManager slotStateManager;
  private StateManager stateManager;

  public Presentation() {
    slotStateManager = new SlotStateManager();
    stateManager = new StateManager();
    this.lastSelectedSlideView = 0;
    this.authorName = "Unknown";
    this.imageURL = getClass().getResource("images/default.jpg");
  }

  @Override
  public void setName(String name, int size) {
    super.setName("Presentation " + size);
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
    notifySubscribers(this);
  }

  public void setImageURL(URL imageURL) {
    this.imageURL = imageURL;
    notifySubscribers(this);
  }

  // SLOT STATES
  public void startSelectSlotState() {
    this.slotStateManager.setSelectSlotState();
  }
  public void startAddSlotState() {
    this.slotStateManager.setAddSlotState();
  }
  public void setAddSlotStateColor(Color color) {
    this.slotStateManager.getAddSlotState().setColor(color);
  }
  public Color getAddSlotStateColor() {
    return this.slotStateManager.getAddSlotState().getColor();
  }
  public void setAddSlotStateInterruptedStroke(boolean interruptedStroke) {
    this.slotStateManager.getAddSlotState().setInterruptedStroke(interruptedStroke);
  }
  public boolean isAddSlotStateInterruptedStroke() {
    return this.slotStateManager.getAddSlotState().isInterruptedStroke();
  }
  public void setAddSlotStateLineWidth(int lineWidth) {
    this.slotStateManager.getAddSlotState().setLineWidth(lineWidth);
  }
  public int getAddSlotStateLineWidth() {
    return this.slotStateManager.getAddSlotState().getLineWidth();
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
  public void startMouseClick(Slot slot, Point position) {
    this.slotStateManager.getCurrentSlotState().mouseClick(slot, position);
  }
  public SlotState getCurrentSlotState() {
    return this.slotStateManager.getCurrentSlotState();
  }
  public void setCurrentSlotState(SlotState slotState) {
    this.slotStateManager.setCurrentSlotState(slotState);
  }
  public SlotState getBeforeCurrentSlotState() {
    return this.slotStateManager.getBeforeCurrentSlotState();
  }

  //SLIDESHOW STATES
  public void startEditState() {
    this.stateManager.setEditState();
  }
  public void startSlideshowState() {
    this.stateManager.setSlideshowState();
  }
  public State getCurrentState() {
    return stateManager.getCurrentState();
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