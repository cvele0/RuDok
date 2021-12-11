package view;

import controller.edit.MouseClickController;
import lombok.Getter;
import model.Presentation;
import model.Slide;
import model.Slot;
import observer.ISubscriber;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Getter

public class SlideView extends JPanel implements ISubscriber {
  private Slide slide;

  private JLabel nameLabel;

  List<RectangleSlotView> rectangles;

  public SlideView(Slide slide) {
    this.slide = slide;
    if (this.slide != null) {
      this.slide.addSubscriber(this);
    }
    initialize();
    update(this);
  }

  public void setSlide(Slide slide) {
    if (this.slide != null) {
      this.slide.removeSubscriber(this);
    }
    this.slide = slide;
    if (this.slide != null) {
      this.slide.addSubscriber(this);
    }
    update(this);
  }

  private void initialize() {
    nameLabel = new JLabel();
    rectangles = new ArrayList<>();

    //centerPanel.setBackground(new Color(236, 149, 62));
    setPreferredSize(new Dimension(400, 300));
    setMaximumSize(new Dimension(400, 300));
    setLayout(new BorderLayout());

    nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
    nameLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
    nameLabel.setForeground(Color.BLACK);
  }

  @Override
  public void update(Object notification) {
    if (this.slide != null) {
      nameLabel.setText(this.slide.getName());
    } else {
      nameLabel.setText("Default");
    }

    removeAll();
    if (this.slide != null) {
      WorkPanel workPanel = new WorkPanel(((Presentation) this.slide.getParent()).getImageURL());
      add(workPanel, BorderLayout.CENTER);
      MouseClickController mouseClickController = new MouseClickController(this);
      workPanel.addMouseListener(mouseClickController);
      workPanel.addMouseMotionListener(mouseClickController);
    }
    add(nameLabel, BorderLayout.NORTH);
  }

  class WorkPanel extends JPanel {
    private Image img;

    public WorkPanel(URL url) {
      this.img = new ImageIcon(url).getImage();
    }

    public void paintComponent(Graphics g) {
      g.drawImage(img, 0, 0, getWidth(), getHeight(), null);

      rectangles.clear();
      for (Slot slot : getSlide().getSlots()) {
        RectangleSlotView r = new RectangleSlotView(slot);
        rectangles.add(r);
        r.paint(g);
      }
    }
  }
}