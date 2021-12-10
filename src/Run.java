import view.MainFrame;

public class Run {
  public static void main(String[] args) {
    MainFrame mainFrame = MainFrame.getInstance();
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setVisible(true);
  }
}

//TODO popraviti slideshow view (4)
//TODO dodati listener na thumbnails (okvir i selection) (3)