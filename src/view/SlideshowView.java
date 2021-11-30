package view;

import model.workspace.Presentation;
import model.workspace.Slide;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class SlideshowView extends JPanel {
  private Presentation presentation;

  private JPanel centerPanel;
  private JPanel buttonPanel;

  private JButton nextBtn;
  private JButton prevBtn;

  private CardLayout cardLayout;

  public SlideshowView(Presentation presentation) {
    this.presentation = presentation;
    initialize();
    addElements();
  }

  private void initialize() {
    centerPanel = new JPanel();
    buttonPanel = new JPanel();
    cardLayout = new CardLayout();

    prevBtn = new JButton();
    nextBtn = new JButton();

    setLayout(new BorderLayout());

    // BUTTONS
    BoxLayout boxLayout = new BoxLayout(buttonPanel, BoxLayout.X_AXIS);
    buttonPanel.setLayout(boxLayout);
    buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttonPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

    Icon icon1 = loadIcon("images/next25x25.png");
    Icon icon2 = loadIcon("images/prev25x25.png");
    nextBtn.setIcon(icon1);
    prevBtn.setIcon(icon2);

    nextBtn.addActionListener(e -> {
      this.cardLayout.next(this.centerPanel);
    });

    prevBtn.addActionListener(e -> {
      this.cardLayout.previous(this.centerPanel);
    });

    /// CENTER PANEL
    centerPanel.setLayout(cardLayout);
  }

  private void addElements() {
    buttonPanel.add(Box.createHorizontalGlue());
    buttonPanel.add(prevBtn);
    buttonPanel.add(Box.createHorizontalStrut(50));
    buttonPanel.add(nextBtn);
    buttonPanel.add(Box.createHorizontalGlue());

    for (int i = 0; i < this.presentation.getChildren().size(); i++) {
      SlideView slideView = new SlideView((Slide) this.presentation.getChildren().get(i));
      centerPanel.add(slideView, "slide" + i);
    }

    add(centerPanel, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);
  }

  public Icon loadIcon(String fileName) {
    URL imageURL = getClass().getResource(fileName);
    Icon icon = null;

    if (imageURL != null) {
      icon = new ImageIcon(imageURL);
    } else {
      System.err.println("Resource not found " + fileName);
    }

    return icon;
  }
}
