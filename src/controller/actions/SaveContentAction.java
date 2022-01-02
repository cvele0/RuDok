package controller.actions;

import lombok.Setter;
import view.content.SlotHandler;

import java.awt.event.ActionEvent;

@Setter

public class SaveContentAction extends AbstractRudokAction {
  private SlotHandler slotHandler;

  public SaveContentAction() {
    putValue(SMALL_ICON, loadIcon("images/save25x25.png"));
    putValue(NAME, "Save content");
    putValue(SHORT_DESCRIPTION, "Save content");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.slotHandler.setContent();
  }
}
