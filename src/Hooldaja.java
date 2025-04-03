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


    public void automaadisTooteid(Müügiautomaat automaat) {
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
        System.out.println("Sisesta väljavõetav summa: ");
        double väljavõetavRaha = Double.parseDouble(sc.nextLine());

        if (automaat.getRahaAutomaadis() - väljavõetavRaha >= 0) { // kui automaadis on piisavalt raha
            automaat.setRahaAutomaadis(automaat.getRahaAutomaadis() - väljavõetavRaha); // salvestame allesjäänud raha
        }
        else {
            System.out.println("Sisestasid liiga suure summa. Palun sisesta väiksem summa.");
        }
    }

    public Tooted otsitavToode(Müügiautomaat müügiautomaat, String otsitavToode) {
        List<Tooted> tooted = müügiautomaat.getTooted();
        for (Tooted toode : tooted) {
            if (toode.getTootenimetus().equals(otsitavToode)) {
                return toode;
            }
        }
        return null;
    }

    public void misHooldusTöidVajaTeha(Hooldaja hooldaja, Müügiautomaat müügiautomaat) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1 Palju on automaadis raha?");
        System.out.println("2 Millised tooted on automaadis?");
        System.out.println("3 Võtta kogu raha automaadist välja");
        System.out.println("4 Võtta osa raha automaadist välja");
        System.out.println("5 Konkreetse toote kohta infot");
        System.out.println("6 Mitu tükki on kindlat toodet veel automaadis alles");
        System.out.println("0 Soovin hoolduse lõpetada");

        while (true) {
            System.out.println("Sisesta tegevuse number, mida soovid teha");
            int tegevusenr = Integer.parseInt(sc.nextLine());

            switch (tegevusenr) {
                case 1:
                    hooldaja.automaadisRaha(müügiautomaat);
                    continue;
                case 2:
                    hooldaja.automaadisTooteid(müügiautomaat);
                    continue;
                case 3:
                    hooldaja.võtaKoguRahaVälja(müügiautomaat);
                    continue;
                case 4:
                    hooldaja.võtaOsaliseltRahaVälja(müügiautomaat);
                    continue;
                case 5:
                    System.out.println("Sisesta toote nimi, mille kohta tahad infot saada");
                    String otsitav1 = sc.nextLine();
                    Tooted toodeMilleKohtaInfo = otsitavToode(müügiautomaat, otsitav1);
                    System.out.println(toodeMilleKohtaInfo.toString());
                    continue;
                case 6:
                    System.out.println("Sisesta toote nimi, mille kohta tahad infot saada");
                    String otsitav2 = sc.nextLine();
                    Tooted toodeMituTükkiOtsime = otsitavToode(müügiautomaat, otsitav2);
                    System.out.println("Toodet " + otsitav2 + " on alles veel " + müügiautomaat.mituTükkiOnToodet(toodeMituTükkiOtsime) + " tükki");
                    continue;

            }
            if (tegevusenr == 0) break;
        }
    }

}
