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
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class PresentationView extends JPanel implements ISubscriber {
  private Presentation presentation;

  private List<SlideView> slideViewList = new ArrayList<>();

  private JPanel jPanel;
  private JLabel jLabel;

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
      this.presentation.getSubscribers().remove(this);
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

    boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
    jPanel.setLayout(boxLayout);

    if (this.presentation != null) {
      jLabel.setText("Author: " + this.presentation.getAuthorName());
    }
    jLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
    jLabel.setForeground(Color.BLUE);
    jLabel.setBorder(new EmptyBorder(0, 10, 0, 0));

    setLayout(new BorderLayout());
  }

  private void addElements() {
    add(jLabel, BorderLayout.NORTH);
    add(jPanel, BorderLayout.CENTER);
  }

  @Override
  public void update(Object notification) {
    if (this.presentation != null) {
      jLabel.setText("Author: " + this.presentation.getAuthorName());
    }

    slideViewList.clear();
    jPanel.removeAll();
    if (this.presentation != null) {
      for (RuNode item : this.presentation.getChildren()) {
        SlideView slideView = new SlideView((Slide) item);
        slideViewList.add(slideView);

        jPanel.add(slideView);
        jPanel.add(Box.createRigidArea(new Dimension(0, 20)));
      }
    }
  }
}
