package state.slot;

import model.workspace.Slide;
import model.workspace.Slot;

import java.awt.*;

public interface SlotState {
  void mouseClick(Slide slide, Slot slot, Point position);
  void mouseClick(Slot slot, Point position);
}
