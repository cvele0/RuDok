package controller.actions;

import lombok.Setter;
import view.content.MultimediaEditor;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.io.File;

@Setter

public class OpenFileAction extends AbstractRudokAction {
  private JFrame editor;

  public OpenFileAction() {
    putValue(SMALL_ICON, loadIcon("images/open25x25.png"));
    putValue(NAME, "Open file");
    putValue(SHORT_DESCRIPTION, "Open file");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    int returnValue = jfc.showOpenDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = jfc.getSelectedFile();
      ((MultimediaEditor) editor).setLastSelectedFilePath(selectedFile.getAbsolutePath());
      editor.repaint();
    }
  }
}
