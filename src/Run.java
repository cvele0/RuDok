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
//TODO dodati jos opcija: debljina okvira, vrsta linije, mozda fill i velicinu pravougaonika (1)
//TODO *** popraviti add metodu sa factory method (2)

//TODO selekcija odabranog slota (option)