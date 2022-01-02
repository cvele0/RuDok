package controller.actions;

import lombok.Setter;

import javax.swing.text.*;
import java.awt.event.ActionEvent;

@Setter

public class BoldAction extends AbstractRudokAction {
  public BoldAction() {
    putValue(SMALL_ICON, loadIcon("images/bold25x25.png"));
    putValue(NAME, "Bold");
    putValue(SHORT_DESCRIPTION, "Bold");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    new StyledEditorKit.BoldAction().actionPerformed(e);
  }
}
