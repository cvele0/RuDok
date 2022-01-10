package view;

import lombok.Getter;
import model.Presentation;
import model.Slide;
import model.Slot;
import observer.ISubscriber;
import view.rectangle.SlideshowRectangleSlotView;
import view.rectangle.SmallRectangleSlotView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Getter

public class SlideshowSlideView extends JPanel implements ISubscriber {
  private Slide slide;

  private JLabel nameLabel;

  private List<SlideshowRectangleSlotView> rectangles;

  public SlideshowSlideView(Slide slide) {
    this.slide = slide;
    if (this.slide != null) {
      this.slide.addSubscriber(this);
    }
    initialize();
    update(this);
  }

  private void initialize() {
    nameLabel = new JLabel();
    rectangles = new ArrayList<>();

    setPreferredSize(new Dimension(800, 620));
    setMaximumSize(new Dimension(800, 620));
    setLayout(new BorderLayout());

    nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
    nameLabel.setBorder(new EmptyBorder(0, 15, 0, 0));
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
        SlideshowRectangleSlotView r = new SlideshowRectangleSlotView(slot);
        rectangles.add(r);
        r.paint(g);
      }
    }
  }
}
