package view.content;

import lombok.Getter;
import lombok.Setter;
import view.MainFrame;
import view.Toolbar;
import view.rectangle.RectangleSlotView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

@Getter
@Setter

public class MultimediaEditor extends JFrame {
  private Toolbar toolbar;
  private RectangleSlotView slotView;
  private JPanel jPanel;
  private String lastSelectedFilePath;

  public MultimediaEditor(RectangleSlotView slotView) {
    this.slotView = slotView;
    setButtonsForMultimediaEditor();
    initialize();
    addElements();
    readImage();
  }

  private void setButtonsForMultimediaEditor() {
    MainFrame.getInstance().getActionManager().getSaveContentAction().setSlotHandler(slotView.getSlotHandler());
    MainFrame.getInstance().getActionManager().getOpenFileAction().setEditor(this);
  }

  private void initialize() {
    // size
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    //specifications
    setSize(screenWidth / 4, screenHeight / 3);
    Image image = toolkit.getImage("src/images/icon.png");
    setIconImage(image);

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        MainFrame.getInstance().refresh();
      }
    });

    setTitle("Multimedia Editor");
    setLocationRelativeTo(null);
  }

  private void addElements() {
    toolbar = new Toolbar();
    toolbar.setEditMultimediaMode();

    jPanel = new JPanel();

    add(toolbar, BorderLayout.NORTH);
    add(jPanel, BorderLayout.CENTER);
  }

  public void reloadImage() {
    WorkPanel workPanel = null;
    try {
      workPanel = new WorkPanel(new File(this.lastSelectedFilePath).toURI().toURL());
    } catch (MalformedURLException e) {
      System.err.println("Invalid multimedia resource.");
    }
    jPanel.removeAll();
    if (workPanel != null) {
      workPanel.setMinimumSize(new Dimension(200, 200));
      workPanel.setPreferredSize(new Dimension(200, 200));
    }
    jPanel.add(workPanel);
    revalidate();
  }

  private void readImage() {
    jPanel.removeAll();
    try {
      if (this.slotView.getSlot().getSlotContent() != null &&
          this.slotView.getSlot().getSlotContent().length() > 0) {
        WorkPanel workPanel = new WorkPanel(new File(this.slotView.getSlot().getSlotContent()).toURI().toURL());
        jPanel.add(workPanel);
        workPanel.setMinimumSize(new Dimension(200, 200));
        workPanel.setPreferredSize(new Dimension(200, 200));
      }
    } catch (MalformedURLException e) {
      System.err.println("Invalid multimedia resource.");
    }
  }

  public void setLastSelectedFilePath(String lastSelectedFilePath) {
    this.lastSelectedFilePath = lastSelectedFilePath;
    reloadImage();
  }

  class WorkPanel extends JPanel {
    private Image img;

    public WorkPanel(URL url) {
      this.img = new ImageIcon(url).getImage();
    }

    public void paintComponent(Graphics g) {
      g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }
  }
}
