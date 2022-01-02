package controller.actions;

import lombok.Setter;

import javax.swing.text.*;
import java.awt.event.ActionEvent;

@Setter

public class UnderlineAction extends AbstractRudokAction {
  public UnderlineAction() {
    putValue(SMALL_ICON, loadIcon("images/underline25x25.png"));
    putValue(NAME, "Underline");
    putValue(SHORT_DESCRIPTION, "Underline");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    new StyledEditorKit.UnderlineAction().actionPerformed(e);
  }
}
