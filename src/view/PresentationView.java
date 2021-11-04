package view;

import lombok.Getter;
import lombok.Setter;
import model.workspace.Presentation;
import model.workspace.Project;
import model.workspace.RuNode;
import model.workspace.Slide;
import observer.ISubscriber;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter

public class PresentationView extends JPanel implements ISubscriber {
  private Presentation presentation;

  List<SlideView> slideViewList = new ArrayList<>();

  public PresentationView() {
    presentation = null;
    initialize();
  }

  public void setModel(Presentation presentation) {
    this.presentation = presentation;
    this.presentation.addSubscriber(this);
  }

  public PresentationView(Presentation presentation) {
    this.presentation = presentation;
    this.presentation.addSubscriber(this);
    initialize();
  }

  private void initialize() {
  }

  @Override
  public void update(Object notification) {

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PresentationView that = (PresentationView) o;
    return Objects.equals(presentation, that.presentation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(presentation);
  }
}
