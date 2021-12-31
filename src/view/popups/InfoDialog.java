package view.popups;

import view.MainFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InfoDialog extends JDialog {
  public InfoDialog() {
    super(MainFrame.getInstance(), "Project Author Info", true);
    initialize();
    addItems();
  }

  private void initialize() {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    int screenHeight = screenSize.height * 3 / 8;
    int screenWidth = screenSize.width / 4;

    Image image = toolkit.getImage("src/images/avatar.png");
    setIconImage(image);

    setSize(new Dimension(screenWidth, screenHeight));
    setLocationRelativeTo(MainFrame.getInstance());

    setLayout(new BorderLayout());
  }

  private void addItems() {
    ImagePanel imagePanel = new ImagePanel("src/images/author2.jpg");

    JPanel textPanel = new JPanel();
    BoxLayout boxLayout = new BoxLayout(textPanel, BoxLayout.X_AXIS);

    JTextArea textArea = new JTextArea("Vladan Cvjetkovic RN19/20");
    textArea.setEditable(false);
    textArea.setFont(new Font("Times New Roman", Font.BOLD, 22));

    textPanel.add(textArea);
    textPanel.setBorder(new EmptyBorder(25, 10, 10, 10));

    add(imagePanel, BorderLayout.CENTER);
    add(textPanel, BorderLayout.NORTH);
  }

  class ImagePanel extends JPanel {
    private Image img;

    public ImagePanel(String img) {
      this(new ImageIcon(img).getImage());
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
