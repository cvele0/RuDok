package view;

import model.workspace.Presentation;
import model.workspace.Slide;
import observer.ISubscriber;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class SlideView extends JPanel implements ISubscriber {
  private Slide slide;

  private JLabel nameLabel;

  private JPanel centerPanel;

  public SlideView() {
    this.slide = null;
    initialize();
    update(this);
  }

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
    centerPanel = new JPanel();

    centerPanel.setBackground(new Color(236, 149, 62));
    setPreferredSize(new Dimension(400, 300));
    setMaximumSize(new Dimension(400, 300));
    setLayout(new BorderLayout());

    nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
    nameLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
    nameLabel.setForeground(Color.BLACK);

    add(nameLabel, BorderLayout.NORTH);
    add(centerPanel, BorderLayout.CENTER);
  }

  @Override
  public void update(Object notification) {
    if (this.slide != null) {
      nameLabel.setText(this.slide.getName());
    } else {
      nameLabel.setText("Default");
    }

    removeAll();
    if (((Presentation) this.slide.getParent()).getImageURL() != null) {
      add(new ImagePanel(((Presentation) this.slide.getParent()).getImageURL()), BorderLayout.CENTER);
    } else {
      add(centerPanel, BorderLayout.CENTER);
    }
    add(nameLabel, BorderLayout.NORTH);
  }

  class ImagePanel extends JPanel {
    private Image img;

    public ImagePanel(URL url) {
      this(new ImageIcon(url).getImage());
    }

    public ImagePanel(Image img) {
      this.img = img;
    }

    public void paintComponent(Graphics g) {
      g.drawImage(img, (int) (this.getSize().getWidth() - img.getWidth(null)) / 2,
              (int) (this.getSize().getHeight() - img.getHeight(null)) / 2, null);
    }
  }
}