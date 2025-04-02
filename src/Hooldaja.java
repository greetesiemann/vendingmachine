import java.util.List;
import java.util.Scanner;

public class Hooldaja {
    private String nimi;
    private String parool;

    public Hooldaja(String nimi, String parool) {
        this.nimi = nimi;
        this.parool = parool;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getParool() {
        return parool;
    }

    public void setParool(String parool) {
        this.parool = parool;
    }

    public boolean kontrolliParool(String sisestatudParool) {
        if (sisestatudParool.equals(parool)) {
            return true;
        } else {
            return false;
        }
    }

    public void automaadisRaha (Müügiautomaat automaat){
        System.out.println(automaat.getRahaAutomaadis());
    }


    public void automamadisTooteid (Müügiautomaat automaat) {
        List<Tooted> tooted = automaat.getTooted();
        System.out.println("Automaadis olevad tooted: ");
        for (int i = 0; i < tooted.size(); i++) {
            System.out.println(tooted.get(i));
        }
    }
    public void võtaKoguRahaVälja (Müügiautomaat automaat) {
        System.out.println("Väljavõetav summa on: " + automaat.getRahaAutomaadis());
        automaat.setRahaAutomaadis(0);
    }

    public void võtaOsaliseltRahaVälja (Müügiautomaat automaat) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Automaadis on raha: " + automaat.getRahaAutomaadis());
        System.out.println("Sisesta väljavõtetav summa: ");
        double väljavõetavRaha = Double.parseDouble(sc.nextLine());

        if (automaat.getRahaAutomaadis() - väljavõetavRaha >= 0) { // kui automaadis on piisavalt raha
            automaat.setRahaAutomaadis(automaat.getRahaAutomaadis() - väljavõetavRaha); // salvestame allesjäänud raha
        }
        else {
            System.out.println("Sisestasid liiga suure summa. Palun sisesta väiksem summa.");
        }
    }

}
