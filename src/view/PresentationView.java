package view;

import lombok.Getter;
import lombok.Setter;
import model.workspace.Presentation;
import model.workspace.RuNode;
import model.workspace.Slide;
import observer.ISubscriber;
import state.slot.AddSlotState;
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

    selectSlotBtn = new JButton();
    addSlotBtn = new JButton();

    removeSlotBtn = new JButton();
    changeColorBtn = new JButton();

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

      // BUTTONS
      toolbarPanel.add(jLabel);
      toolbarPanel.add(Box.createHorizontalStrut(40));
      toolbarPanel.add(selectSlotBtn);
      toolbarPanel.add(Box.createHorizontalStrut(10));
      toolbarPanel.add(addSlotBtn);
      toolbarPanel.add(Box.createHorizontalStrut(10));
      toolbarPanel.add(removeSlotBtn);
      toolbarPanel.add(Box.createHorizontalStrut(10));
      toolbarPanel.add(changeColorBtn);

      Icon icon1 = loadIcon("images/addRectangle25x25.png");
      addSlotBtn.setIcon(icon1);
      addSlotBtn.setMaximumSize(new Dimension(35, 35));

      Icon icon2 = loadIcon("images/removeRectangle25x25.png");
      removeSlotBtn.setIcon(icon2);
      removeSlotBtn.setMaximumSize(new Dimension(35, 35));

      Icon icon3 = loadIcon("images/select25x25.png");
      selectSlotBtn.setIcon(icon3);
      selectSlotBtn.setMaximumSize(new Dimension(35, 35));

      Icon icon4 = loadIcon("images/paint25x25.png");
      changeColorBtn.setIcon(icon4);
      changeColorBtn.setMaximumSize(new Dimension(35, 35));
      changeColorBtn.setBackground((this.presentation.getSlotStateManager().getAddSlotState().getColor()));

      // CURRENT STATE COLOR
      if (this.presentation.getSlotStateManager().getCurrentSlotState() instanceof AddSlotState) {
        addSlotBtn.setBackground(selectedStateColor);
      } else if (this.presentation.getSlotStateManager().getCurrentSlotState() instanceof RemoveSlotState) {
        removeSlotBtn.setBackground(selectedStateColor);
      } else if (this.presentation.getSlotStateManager().getCurrentSlotState() instanceof SelectSlotState) {
        selectSlotBtn.setBackground(selectedStateColor);
      }

      // LISTENERS FOR BUTTONS
        selectSlotBtn.addActionListener(e -> {
          this.presentation.startSelectSlotState();
          MainFrame.getInstance().refresh(); // Mora refresh radi bojenja trenutnog izabranog statea
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
          this.presentation.getSlotStateManager().getAddSlotState().setColor(color);
          changeColorBtn.setBackground(color);
        });

    setLayout(new BorderLayout());
  }

  private void addElements() {
    add(toolbarPanel, BorderLayout.NORTH);
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
        SmallSlideView smallSlideView = new SmallSlideView((Slide) item);

        leftPanel.add(smallSlideView);
        leftPanel.add(Box.createVerticalStrut(30));
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