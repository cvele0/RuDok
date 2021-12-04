package model.workspace;

import lombok.Getter;
import lombok.Setter;
import observer.IPublisher;
import observer.ISubscriber;
import state.slot.SlotStateManager;
import view.MainFrame;
import view.SlideView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class Presentation extends RuNodeComposite {
  private String authorName;
  private URL imageURL;

  private SlotStateManager slotStateManager;

  public Presentation(RuNode parent, String name) {
    super(parent, name);
    slotStateManager = new SlotStateManager();
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

  public void startMouseClick(SlideView slideView, Point position) {
    this.slotStateManager.getCurrentSlotState().mouseClick(slideView, position);
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