package view;

import controller.edit.SmallViewEnterMouseController;
import controller.edit.SmallViewExitMouseController;
import javafx.scene.layout.BorderStroke;
import lombok.Getter;
import lombok.Setter;
import model.Presentation;
import model.RuNode;
import model.Slide;
import observer.ISubscriber;
import state.slideshow.SlideshowState;
import state.slot.AddSlotState;
import state.slot.MoveSlotState;
import state.slot.RemoveSlotState;
import state.slot.SelectSlotState;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

@Getter
@Setter

public class PresentationView extends JPanel implements ISubscriber {
  private Presentation presentation;

  private JPanel jPanel;
  private JLabel jLabel;
  private JPanel leftPanel;
  private JPanel toolbarPanel;

  private JScrollPane jScrollPane;
  private JScrollPane leftScroll;

  private JSplitPane jSplitPane;

  private BoxLayout boxLayout;

  private JButton selectSlotBtn;
  private JButton addSlotBtn;
  private JButton removeSlotBtn;
  private JButton changeColorBtn;
  private JButton slideShowBtn;
  private JButton moveSlotBtn;
  private JButton strokeBtn;

  private static Color selectedStateColor = new Color(112, 155, 249);

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
    toolbarPanel = new JPanel();

    setButtons();

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

    //TOOLBAR PANEL
    BoxLayout boxLayout2 = new BoxLayout(toolbarPanel, BoxLayout.X_AXIS);
    toolbarPanel.setLayout(boxLayout2);

    toolbarPanel.add(jLabel);
    toolbarPanel.add(Box.createHorizontalStrut(40));
    toolbarPanel.add(selectSlotBtn);
    toolbarPanel.add(Box.createHorizontalStrut(10));
    toolbarPanel.add(addSlotBtn);
    toolbarPanel.add(Box.createHorizontalStrut(10));
    toolbarPanel.add(removeSlotBtn);
    toolbarPanel.add(Box.createHorizontalStrut(10));
    toolbarPanel.add(moveSlotBtn);
    toolbarPanel.add(Box.createHorizontalStrut(10));
    toolbarPanel.add(slideShowBtn);
    toolbarPanel.add(Box.createHorizontalStrut(10));
    toolbarPanel.add(strokeBtn);
    toolbarPanel.add(Box.createHorizontalStrut(10));
    toolbarPanel.add(changeColorBtn);

    //toolbarPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(0.2f)));
    setLayout(new BorderLayout());
  }

  private void loadButton(JButton button, String name) {
    Icon icon = loadIcon(name);
    button.setIcon(icon);
    button.setMaximumSize(new Dimension(35, 35));
    button.setPreferredSize(new Dimension(35, 35));
  }

  private void setButtons() {
    selectSlotBtn = new JButton();
    addSlotBtn = new JButton();
    removeSlotBtn = new JButton();
    changeColorBtn = new JButton();
    slideShowBtn = new JButton();
    moveSlotBtn = new JButton();
    strokeBtn = new JButton();

    loadButton(addSlotBtn, "images/addRectangle25x25.png");
    loadButton(removeSlotBtn, "images/removeRectangle25x25.png");
    loadButton(selectSlotBtn, "images/select25x25.png");
    loadButton(changeColorBtn, "images/paint25x25.png");
    loadButton(slideShowBtn, "images/slideshow25x25.png");
    loadButton(moveSlotBtn, "images/move25x25.png");
    loadButton(strokeBtn, "images/stroke25x25.png");

    changeColorBtn.setBackground((this.presentation.getAddSlotStateColor()));

    // CURRENT STATE COLOR
    if (this.presentation.getCurrentSlotState() instanceof AddSlotState) {
      addSlotBtn.setBackground(selectedStateColor);
    } else if (this.presentation.getCurrentSlotState() instanceof RemoveSlotState) {
      removeSlotBtn.setBackground(selectedStateColor);
    } else if (this.presentation.getCurrentSlotState() instanceof SelectSlotState) {
      selectSlotBtn.setBackground(selectedStateColor);
    } else if (this.presentation.getCurrentSlotState() instanceof MoveSlotState) {
      moveSlotBtn.setBackground(selectedStateColor);
    }

    // LISTENERS FOR BUTTONS
    selectSlotBtn.addActionListener(e -> {
      this.presentation.startSelectSlotState();
      MainFrame.getInstance().refresh(); //Painting selected state
    });

    addSlotBtn.addActionListener(e -> {
      this.presentation.startAddSlotState();
      MainFrame.getInstance().refresh();
    });

    removeSlotBtn.addActionListener(e -> {
      this.presentation.startRemoveSlotState();
      MainFrame.getInstance().refresh();
    });

    changeColorBtn.addActionListener(e -> {
      Color color = JColorChooser.showDialog(null, "Please select a color", Color.RED);
      this.presentation.setAddSlotStateColor(color);
      changeColorBtn.setBackground(color);
    });

    slideShowBtn.addActionListener(e -> {
      this.presentation.startSelectSlotState();
      this.presentation.startSlideshowState();
      MainFrame.getInstance().refresh();
    });

    moveSlotBtn.addActionListener(e -> {
      this.presentation.startMoveSlotState();
      MainFrame.getInstance().refresh();
    });

    strokeBtn.addActionListener(e -> {
      EditStrokeDialog editStrokeDialog = new EditStrokeDialog(this.presentation);
      editStrokeDialog.setVisible(true);
    });
  }

  private void addElements() {
    removeAll();
    if (this.presentation != null) {
      if (this.presentation.getCurrentState() instanceof SlideshowState) {
        add(new SlideshowView(this.presentation), BorderLayout.CENTER);
      } else {
        add(toolbarPanel, BorderLayout.NORTH);
        add(jSplitPane, BorderLayout.CENTER);
      }
    }
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

    getJScrollPane().getViewport().setViewPosition(new Point(0,
            this.presentation.getLastSelectedSlideView() * 300 + this.presentation.getLastSelectedSlideView() * 30));

    leftPanel.removeAll();
    leftPanel.add(Box.createVerticalStrut(5));
    if (this.presentation != null) {
      int indexNumber = 0;
      for (RuNode item : this.presentation.getChildren()) {
        SmallSlideView smallSlideView = new SmallSlideView((Slide) item);

        if (((Slide) item).isSelectedThumbnail()) {
          smallSlideView.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2f), new Color(252, 47, 44)));
          smallSlideView.addMouseListener(new SmallViewExitMouseController(this, smallSlideView, indexNumber++));
        } else {
          smallSlideView.setBorder(null);
          smallSlideView.addMouseListener(new SmallViewEnterMouseController(this, smallSlideView, indexNumber++));
        }

        leftPanel.add(smallSlideView);
        leftPanel.add(Box.createVerticalStrut(15));
      }
    }
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