import view.MainFrame;

public class Run {
  public static void main(String[] args) {
    MainFrame mainFrame = MainFrame.getInstance();
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setVisible(true);
  }
}
