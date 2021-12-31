package controller.actions;

import lombok.Setter;
import view.content.MultimediaEditor;
import view.content.TextEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;

@Setter

public class SaveContentAction extends AbstractRudokAction {
  private JFrame editor;

  public SaveContentAction() {
    putValue(SMALL_ICON, loadIcon("images/save25x25.png"));
    putValue(NAME, "Save content");
    putValue(SHORT_DESCRIPTION, "Save content");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (editor instanceof TextEditor) {
      ((TextEditor) editor).getSlotView().getSlotHandler().setContent();
    } else {
      ((MultimediaEditor) editor).getSlotView().getSlotHandler().setContent();
    }
  }
}
