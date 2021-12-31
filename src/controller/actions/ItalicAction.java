package controller.actions;

import lombok.Setter;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.ActionEvent;

@Setter

public class ItalicAction extends AbstractRudokAction {
  private JFrame editor;

  public ItalicAction() {
    putValue(SMALL_ICON, loadIcon("images/italic25x25.png"));
    putValue(NAME, "Italic");
    putValue(SHORT_DESCRIPTION, "Italic");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    new StyledEditorKit.ItalicAction().actionPerformed(e);
  }
}
