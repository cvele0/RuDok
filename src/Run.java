import view.MainFrame;

public class Run {
  public static void main(String[] args) {
    MainFrame mainFrame = MainFrame.getInstance();
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setVisible(true);
  }
}

//TODO popraviti slideshow view
//TODO dodati listener na thumbnails (okvir i selection)
//TODO dodati jos opcija: debljina okvira, vrsta linije, mozda fill i velicinu pravougaonika
//TODO *** popraviti add metodu sa factory method