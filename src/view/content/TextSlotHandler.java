package view.content;

import view.rectangle.RectangleSlotView;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

public class TextSlotHandler implements SlotHandler {
  private RectangleSlotView slotView;
  private TextEditor textEditor;

  public TextSlotHandler(RectangleSlotView slotView) {
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
  public void paint(RectangleSlotView rectangleSlotView, Graphics2D g) {
    String hash = rectangleSlotView.getSlot().getSlotContent();

    if (hash == null) return;

    boolean isBold = false;
    boolean isItalic = false;
    boolean isUnderline = false;

    int x = (int) rectangleSlotView.getX() + rectangleSlotView.getXStartDistance();
    int y = (int) rectangleSlotView.getY() + rectangleSlotView.getYStartDistance();
    int wrapLine = 0;

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

      Font font = new Font("Times New Roman", (isBold ? Font.BOLD : Font.PLAIN) +
              (isItalic ? Font.ITALIC : Font.PLAIN), rectangleSlotView.getFontSize());

      g.setFont(font);

      FontMetrics fontMetrics = g.getFontMetrics(g.getFont());
      Rectangle2D bounds = fontMetrics.getStringBounds(hash.charAt(i) + "", g);
      int offsetX = (int) -bounds.getWidth() / 2;
      int offsetY = (int) -bounds.getHeight() / 2;
      g.translate(offsetX, offsetY);

      Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
      fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
      font = font.deriveFont(fontAttributes);

      if (isUnderline) g.setFont(font);

      g.drawString(hash.charAt(i) + "", x, y);
      x += rectangleSlotView.getXDistance();

      g.translate(-offsetX, -offsetY);

      wrapLine++;
      if (wrapLine % rectangleSlotView.getNumberOfLetters() == 0) {
        y += rectangleSlotView.getYDistance();
        x = (int) rectangleSlotView.getX() + rectangleSlotView.getXStartDistance();
        if (wrapLine == rectangleSlotView.getNumberOfLetters() * rectangleSlotView.getNumberOfRows()) return;
      }
    }
  }
}
