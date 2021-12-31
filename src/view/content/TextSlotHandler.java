package view.content;

import view.rectangle.SlideRectangleSlotView;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class TextSlotHandler implements SlotHandler {
  private SlideRectangleSlotView slotView;
  private TextEditor textEditor;

  public TextSlotHandler(SlideRectangleSlotView slotView) {
    this.slotView = slotView;
    this.textEditor = null;
  }

  @Override
  public void readContent() {
    this.textEditor = new TextEditor(this.slotView);
    this.textEditor.setVisible(true);
  }

  @Override
  public void setContent() {
    JTextPane textPane = this.textEditor.getJTextPane();

    StyledDocument doc = (StyledDocument) textPane.getDocument();

    boolean isBold = false;
    boolean isItalic = false;
    boolean isUnderline = false;

    StringBuilder hash = new StringBuilder("");

    for (int i = 0; i < textPane.getText().length(); i++) {
      Element element = doc.getCharacterElement(i);
      AttributeSet as = element.getAttributes();

      if (StyleConstants.isBold(as)) {
        if (!isBold) {
          isBold = true;
          hash.append("/b");
        }
      } else {
        if (isBold) {
          isBold = false;
          hash.append("/b");
        }
      }

      if (StyleConstants.isItalic(as)) {
        if (!isItalic) {
          isItalic = true;
          hash.append("/i");
        }
      } else {
        if (isItalic) {
          isItalic = false;
          hash.append("/i");
        }
      }

      if (StyleConstants.isUnderline(as)) {
        if (!isUnderline) {
          isUnderline = true;
          hash.append("/u");
        }
      } else {
        if (isUnderline) {
          isUnderline = false;
          hash.append("/u");
        }
      }

      hash.append(textPane.getText().charAt(i));
    }

    this.textEditor.getSlotView().getSlot().setSlotContent(hash.toString());
  }

  @Override
  public void paint() {

  }
}
