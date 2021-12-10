package view;

import model.Presentation;
import model.Slide;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class SlideshowView extends JPanel {
  private Presentation presentation;

  private JPanel centerPanel;
  private JPanel buttonPanel;
  private JPanel toolbarPanel;

  private JButton nextBtn;
  private JButton prevBtn;
  private JButton exitSlideshowBtn;

  private JLabel jLabel;

  private CardLayout cardLayout;

  public SlideshowView(Presentation presentation) {
    this.presentation = presentation;
    initialize();
    addElements();
  }

  private void initialize() {
    jLabel = new JLabel();

    centerPanel = new JPanel();
    buttonPanel = new JPanel();
    toolbarPanel = new JPanel();
    cardLayout = new CardLayout();

    prevBtn = new JButton();
    nextBtn = new JButton();
    exitSlideshowBtn = new JButton();

    setLayout(new BorderLayout());

    jLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
    jLabel.setForeground(new Color(51, 84, 255));
    jLabel.setBorder(new EmptyBorder(10, 10, 10, 0));
    jLabel.setText("Author: " + this.presentation.getAuthorName());

    //TOOLBAR PANEL
    BoxLayout boxLayout1 = new BoxLayout(toolbarPanel, BoxLayout.X_AXIS);
    toolbarPanel.setLayout(boxLayout1);
    toolbarPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    toolbarPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
    //toolbarPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(0.4f)));

    // BUTTONS
    BoxLayout boxLayout = new BoxLayout(buttonPanel, BoxLayout.X_AXIS);
    buttonPanel.setLayout(boxLayout);
    buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttonPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

    exitSlideshowBtn.setPreferredSize(new Dimension(35, 35));
    exitSlideshowBtn.setMaximumSize(new Dimension(35, 35));

    Icon icon1 = loadIcon("images/next25x25.png");
    Icon icon2 = loadIcon("images/prev25x25.png");
    Icon icon3 = loadIcon("images/exitSlideshow25x25.png");
    nextBtn.setIcon(icon1);
    prevBtn.setIcon(icon2);
    exitSlideshowBtn.setIcon(icon3);

    nextBtn.addActionListener(e -> this.cardLayout.next(this.centerPanel));
    prevBtn.addActionListener(e -> this.cardLayout.previous(this.centerPanel));

    exitSlideshowBtn.addActionListener(e -> {
      this.presentation.startEditState();
      MainFrame.getInstance().refresh();
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

    toolbarPanel.add(jLabel);
    toolbarPanel.add(Box.createHorizontalStrut(40));
    toolbarPanel.add(exitSlideshowBtn);

    for (int i = 0; i < this.presentation.getChildren().size(); i++) {
      SlideView slideView = new SlideView((Slide) this.presentation.getChildren().get(i));
      centerPanel.add(slideView, "slide" + i);
    }

    centerPanel.setPreferredSize(new Dimension(400, 300));
    centerPanel.setMinimumSize(new Dimension(400, 300));
    int width = MainFrame.getInstance().getSize().width / 8;
    centerPanel.setBorder(new EmptyBorder(10, width,  10, width));

    add(toolbarPanel, BorderLayout.NORTH);
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
