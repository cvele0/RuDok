package view;

import lombok.Getter;
import lombok.Setter;
import model.workspace.Presentation;
import model.workspace.RuNode;
import model.workspace.Slide;
import observer.ISubscriber;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

@Getter
@Setter

public class PresentationView extends JPanel implements ISubscriber {
  private Presentation presentation;

  private JPanel jPanel;
  private JLabel jLabel;
  private JPanel leftPanel;

  private JScrollPane jScrollPane;
  private JScrollPane leftScroll;

  private JSplitPane jSplitPane;

  private BoxLayout boxLayout;

  public PresentationView() {
    presentation = null;
    initialize();
    addElements();
    update(this);
  }

  public PresentationView(Presentation presentation) {
    this.presentation = presentation;
    if (this.presentation != null) {
      this.presentation.addSubscriber(this);
    }
    initialize();
    addElements();
    update(this);
  }

  public void setPresentation(Presentation presentation) {
    if (this.presentation != null) {
      this.presentation.removeSubscriber(this);
    }
    this.presentation = presentation;
    if (this.presentation != null) {
      this.presentation.addSubscriber(this);
    }
    update(this);
  }

  private void initialize() {
    jLabel = new JLabel();
    jPanel = new JPanel();
    leftPanel = new JPanel();

    // CENTER PANEL
    boxLayout = new BoxLayout(jPanel, BoxLayout.PAGE_AXIS);
    jPanel.setLayout(boxLayout);
    jPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

    jScrollPane = new JScrollPane(jPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    jLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
    jLabel.setForeground(new Color(51, 84, 255));
    jLabel.setBorder(new EmptyBorder(10, 10, 10, 0));

    // LEFT PANEL
    BoxLayout boxLayout1 = new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS);
    leftPanel.setLayout(boxLayout1);
    leftPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

    leftScroll = new JScrollPane(leftPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftScroll, jScrollPane);
    jSplitPane.setDividerLocation(150);
    jSplitPane.setEnabled(false);

    setLayout(new BorderLayout());
  }

  private void addElements() {
    add(jLabel, BorderLayout.NORTH);
    add(jSplitPane, BorderLayout.CENTER);
  }

  @Override
  public void update(Object notification) {
    if (this.presentation != null) {
      jLabel.setText("Author: " + this.presentation.getAuthorName());
    }

    jPanel.removeAll();
    if (this.presentation != null) {
      for (RuNode item : this.presentation.getChildren()) {
        SlideView slideView = new SlideView((Slide) item);

        jPanel.add(slideView);
        jPanel.add(Box.createVerticalStrut(30));
      }
    }

    leftPanel.removeAll();
    if (this.presentation != null) {
      for (RuNode item : this.presentation.getChildren()) {
        SlideView slideView = new SlideView((Slide) item);

        slideView.getNameLabel().setFont(new Font("Times New Roman", Font.BOLD, 13));
        slideView.getNameLabel().setBorder(new EmptyBorder(0, 2, 0, 0));
        slideView.setPreferredSize(new Dimension(100, 75));
        slideView.setMaximumSize(new Dimension(100, 75));

        leftPanel.add(slideView);
        leftPanel.add(Box.createVerticalStrut(30));
      }
    }
  }
}