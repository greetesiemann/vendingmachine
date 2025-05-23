import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Peaklass {

    static List<Tooted> loeTooted(String failinimi) throws IOException, TooteLugemiseViga {
        List<Tooted> tooted = new ArrayList<Tooted>();
        try (BufferedReader br = new BufferedReader(new FileReader(failinimi))) {
            String rida;

            while ((rida = br.readLine()) != null) {
                String[] osad = rida.split(";");
                if (osad.length != 5) throw new TooteLugemiseViga("Vigane rida: " + rida);

                String tootenimi = osad[0];
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
            }return tooted;
        }

    }

    static void salvesta(List<Tooted> allesjäänud_tooted, String failinimi) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(failinimi))) {
            for (Tooted toode : allesjäänud_tooted) {
                String tunnus = "";
                int kaal = 0;

                // kui toode kuulub 'Joogid' klassi
                if (toode.getClass() == Joogid.class) {
                    tunnus = "J";
                    kaal = ((Joogid) toode).getMaht_ml();

                // kui toode kuulub 'Söögid' klassi
                } else if (toode.getClass() == Söögid.class){
                    tunnus = "S";
                    kaal = ((Söögid) toode).getKaal_g();
                }
                // kirjutame tooted tagasi faili
                bw.write(toode.getTootenimetus() + ";" + tunnus + ";" + toode.getHind() + ";" + toode.getMituTükki() + ";" + kaal);
                bw.newLine();
            }
        }
    }

    static void hooldajaTegevus(Hooldaja hooldaja, Müügiautomaat automaat) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Oled hooldaja süsteemis");
        System.out.println("Sisesta parool: ");
        String sisestatudParool = sc.nextLine();
        boolean õigeParool = hooldaja.kontrolliParool(sisestatudParool); //kontrollime parooli

        if (õigeParool) {
            System.out.println("Tere hooldaja " + hooldaja.getNimi());
            System.out.println("Mida soovid teada?"); // väljastame hooldajale tegevused, mida võimalik teha
            hooldaja.misHooldusTöidVajaTeha(hooldaja, automaat);
        } else {
            System.out.println("Sisestasid vale parooli. Palun proovi uuesti!");
        }
    }

    public static void main(String[] args) throws Exception, FileNotFoundException {

        List<Tooted> tooted = loeTooted("tooted.txt"); // loeme tooted
        try {
            for (Tooted toode : tooted) { // seadistame toodetele müügihinna
                toode.tooteMüügiHind();
            }
            Müügiautomaat automaatDelta = new Müügiautomaat("Delta", tooted);
            Hooldaja deltaHooldaja = new Hooldaja("Jaanus Koppel", "Jaanus123"); // loome "Delta" automaadile hooldaja
            while (true) {
                //Uurime, kes on automaadi kasutaja
                Scanner sc = new Scanner(System.in);

                if (tooted.isEmpty()) {
                    hooldajaTegevus(deltaHooldaja, automaatDelta);
                }

                System.out.println("Tere tulemast!");
                System.out.println("Kas oled klient või hooldaja?");
                String vastus = sc.nextLine();

                if (vastus.equals("hooldaja")) {// kui hooldaja siis kutsume välja erinevaid hooldaja meetodeid
                    hooldajaTegevus(deltaHooldaja, automaatDelta);

                } else if (vastus.equals("klient")) {
                    double toodeteKoguSumma = 0;
                    System.out.println("Müügiautomaadis ostmiseks olevad tooted: ");

                    for (int indeks = 0; indeks < tooted.size(); indeks++) { // kui klient siis väljastame tooted, koos vastavate numbrite ja hindadega
                        System.out.println(indeks + " " + tooted.get(indeks).getTootenimetus() + " " + tooted.get(indeks).getHind());
                    }

                    boolean soovinVeelTooteid = true; // muutuja, mis kontrollib kas klient soovib veel tooteid osta
                    while (soovinVeelTooteid) {
                        System.out.println("Vali toode numbri järgi ");
                        System.out.println("Kui ei suuda otsustada, mida tahaksid, siis sisesta '000' ning automaat teeb valiku sinu eest :)");
                        String tootenr = sc.nextLine();
                        if (tootenr.equals("000")) {
                            while (true) { //tsükkel, mis otsib juhusliku toote ja pakub seda kliendile
                                int juhuarv = (int) (Math.random() * tooted.size());
                                Tooted juhuslikultOstetudToode = tooted.get(juhuarv);
                                System.out.println("Juhuslikult valitud tooteks osutus " + juhuslikultOstetudToode.getTootenimetus() + " hinnaga "
                                + juhuslikultOstetudToode.getHind() + "€");
                                System.out.println("Kas oled tootega rahul ja soovid selle osta? ('jah' või 'ei')");
                                String toodeSobib = sc.nextLine();

                                if (toodeSobib.equals("jah")) {
                                    toodeteKoguSumma += juhuslikultOstetudToode.getHind();
                                    System.out.println("Rahasumma on " + toodeteKoguSumma + "€");
                                    System.out.println("Palun võta oma ostetud toode " + juhuslikultOstetudToode.getTootenimetus());
                                    juhuslikultOstetudToode.vähendaToodet();
                                    automaatDelta.lisaRaha(juhuslikultOstetudToode); // lisame automaati raha
                                    if (juhuslikultOstetudToode.getMituTükki() == 0) { // kontrollime, kas toodet on veel alles
                                        automaatDelta.eemaldaToode(juhuslikultOstetudToode); // vajadusel eemaldame selle
                                    }
                                    break;
                                }
                            }

                        } else {
                            try {
                                Tooted ostetudToode = tooted.get(Integer.parseInt(tootenr));
                                // väljastame kliendile toote nimetuse, mis ta ostis
                                System.out.println("Palun võta oma ostetud toode " + ostetudToode.getTootenimetus());
                                toodeteKoguSumma += ostetudToode.getHind();
                                System.out.println("Hetke kogusumma on " + toodeteKoguSumma + "€");
                                ostetudToode.vähendaToodet(); // vähendame ostetud toote arvu
                                automaatDelta.lisaRaha(ostetudToode); // lisame automaati raha

                                if (ostetudToode.getMituTükki() == 0) { // kontrollime, kas toodet on veel alles
                                    automaatDelta.eemaldaToode(ostetudToode); // vajadusel eemaldame selle
                                }

                                if (tooted.isEmpty()) {
                                    System.out.println("Automaadist on tooted otsas");
                                    break;
                                }

                                System.out.println("Müügiautomaadis ostmiseks olevad tooted: "); // kuvame uuesti allesolevad tooted
                                for (int indeks = 0; indeks < tooted.size(); indeks++) {
                                    System.out.println(indeks + " " + tooted.get(indeks).getTootenimetus() + " " + tooted.get(indeks).getHind());
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Palun sisesta korrektne number");
                            }
                        }

                        System.out.println("Kas soovid veel midagi osta? ('jah' või 'ei')");
                        String valik = sc.nextLine();
                        if (valik.equals("ei")) {
                            System.out.println("Aitäh, et meid külastasid! Ilusat päeva jätku!");
                            System.out.println();
                            soovinVeelTooteid = false;
                        }
                    }
                }
                break;
            }
        } finally {
            salvesta(tooted, "tooted.txt");
        }
    }
}
