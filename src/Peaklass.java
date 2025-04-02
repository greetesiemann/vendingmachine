import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Peaklass {
    /**
     * Meetod toodete lugemiseks
     * @param failinimi
     * @return tagastatakse failist loetud toodete list
     * @throws FileNotFoundException
     */
    static List<Tooted> loeTooted(String failinimi) throws FileNotFoundException {
        List<Tooted> tooted = new ArrayList<Tooted>();
        File fail = new File(failinimi);
        Scanner sc = new Scanner(fail, "UTF-8");
        while (sc.hasNextLine()) {
            String rida  = sc.nextLine();
            String[] osad = rida.split(";");
            String tootenimi  = osad[0];
            String tunnus = osad[1];
            double hind = Double.parseDouble(osad[2]);
            int mitutükki = Integer.parseInt(osad[3]);
            int kaal = Integer.parseInt(osad[4]);
            if (tunnus.equals("J")) { //tegemist joogiga
                tooted.add(new Joogid(tootenimi, hind, mitutükki, kaal));
            }
            if (tunnus.equals("S")) { //tegemist söögiga
                tooted.add(new Söögid(tootenimi, hind, mitutükki, kaal));
            }
        }
        return tooted;
    }
    public static void main(String[] args) {
        try {
            List<Tooted> tooted = loeTooted("tooted.txt"); //loeme tooted
            Müügiautomaat automaatDelta = new Müügiautomaat ("Delta");
            Hooldaja deltaHooldaja = new Hooldaja("Jaanus Koppel", "Jaanus123"); // loome "Delta" automaadile hooldaja
            for (int toode = 0; toode < tooted.size(); toode++) { //lisame tooted automaati
                automaatDelta.lisaToode(tooted.get(toode));
            }
            //Uurime, kes on automaadi kasutaja
            Scanner sc = new Scanner(System.in);
            System.out.println("Tere tulemest!");
            System.out.println("Kas oled klient või hooldaja?");
            String vastus = sc.nextLine();

            if (vastus.equals("hooldaja")) {// kui hooldaja siis kutsume välja erinevaid hooldaja meetodeid
                System.out.println("Sisesta parool: ");
                String sisestatudParool = sc.nextLine();
                boolean õigeParool = deltaHooldaja.kontrolliParool(sisestatudParool); //kontorllime parooli
                if (õigeParool) {
                    System.out.println("Tere hooldaja " + deltaHooldaja.getNimi());
                    System.out.println("Mida soovid teada?"); // väljastame hooldajale tegevused, mida võimalik teha
                    // mõtlesin äkki selle switch case'iga
                } else {
                    System.out.println("Sisestasid vale parooli. Palun proovi uuesti!");
                }

            } else if (vastus.equals("klient")) {
                System.out.println("Müügiautomaadis ostmiseks olevad tooted: ");
                for (int indeks = 0; indeks < tooted.size(); indeks++) { // kui klient siis väljastame tooted, koos vastavate numbritega
                    System.out.println(indeks + " " + tooted.get(indeks).getTootenimetus() + " " + tooted.get(indeks).getHind());
                }

                boolean soovinVeelTooteid = true; // muutuja, mis kontrollib kas klient soovib veel tooteid osta
                while (soovinVeelTooteid) {
                    System.out.println("Vali toode: ");
                    String tootenr = sc.nextLine();
                    Tooted ostetudToode = tooted.get(Integer.parseInt(tootenr));
                    //väljastame kleindile toote nimetuse, mis ta ostis
                    System.out.println("Palun võta oma ostetud toode" + ostetudToode.getTootenimetus());
                    ostetudToode.vähendaToodet(); // vähendame ostetud toote arvu
                    if (ostetudToode.getMituTükki() == 0) { // kontorllime, kas toodet on veel alles
                        automaatDelta.eemaldaToode(ostetudToode); //vajadusel eemaldame selle
                    }

                    System.out.println("Kas soovid veel midagi osta?");
                    String valik = sc.nextLine();
                    if (valik.equals("Ei")) {
                        System.out.println("Aitäh, et meid külastsid! Ilusat päeva jätku!");
                        soovinVeelTooteid = false;
                    }
                    else {
                        continue;
                    }
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("Faili ei leitud!");
        }

    }
}
