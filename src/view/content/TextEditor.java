package view.content;

import lombok.Getter;
import lombok.Setter;
import view.MainFrame;
import view.Toolbar;
import view.rectangle.RectangleSlotView;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Getter
@Setter

public class TextEditor extends JFrame {
  private Toolbar toolbar;
  private JTextPane jTextPane;
  private RectangleSlotView slotView;

  public TextEditor(RectangleSlotView slotView) {
    this.slotView = slotView;
    setButtonsForTextEditor();
    initialize();
    addElements();
    readText();
  }

  private void setButtonsForTextEditor() {
    MainFrame.getInstance().getActionManager().getSaveContentAction().setSlotHandler(slotView.getSlotHandler());
  }

  private void initialize() {
    // size
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    //specifications
    setSize(screenWidth / 4, screenHeight / 3);
    Image image = toolkit.getImage("src/images/icon.png");
    setIconImage(image);

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        MainFrame.getInstance().refresh();
      }
    });

    setTitle("Text Editor");
    setLocationRelativeTo(null);
  }

  private void addElements() {
    toolbar = new Toolbar();
    toolbar.setEditTextMode();

    jTextPane = new JTextPane();
    jTextPane.setFont(new Font("Times New Roman", Font.PLAIN, 16));

    add(toolbar, BorderLayout.NORTH);
    add(jTextPane, BorderLayout.CENTER);
  }

  private void readText() {
    String hash = slotView.getSlot().getSlotContent();

    if (hash == null) return;

    boolean isBold = false;
    boolean isItalic = false;
    boolean isUnderline = false;

    for (int i = 0; i < hash.length(); i++) {
      if (hash.charAt(i) == '/' && i + 1 < hash.length()) {
        if (hash.charAt(i + 1) == 'b') {
          isBold = !isBold;
        } else if (hash.charAt(i + 1) == 'i') {
          isItalic = !isItalic;
        } else if (hash.charAt(i + 1) == 'u') {
          isUnderline = !isUnderline;
        }
        i++;
        continue;
      }

      SimpleAttributeSet attributeSet = new SimpleAttributeSet();
      StyleConstants.setBold(attributeSet, isBold);
      StyleConstants.setItalic(attributeSet, isItalic);
      StyleConstants.setUnderline(attributeSet, isUnderline);

      Document doc = getJTextPane().getStyledDocument();
      try {
        doc.insertString(doc.getLength(), hash.charAt(i) + "", attributeSet);
      } catch (BadLocationException e) {
        e.printStackTrace();
      }
    }
  }
}
