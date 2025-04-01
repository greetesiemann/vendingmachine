import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Peaklass {
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
            if (tunnus.equals("J")) {
                tooted.add(new Joogid(tootenimi, hind, mitutükki, kaal));
            }
            if (tunnus.equals("S")) {
                tooted.add(new Söögid(tootenimi, hind, mitutükki, kaal));
            }
        }
        return tooted;
    }
    public static void main(String[] args) {
        try {
            List<Tooted> tooted = loeTooted("tooted.txt");

        }
        catch (FileNotFoundException e) {
            System.out.println("Faili ei leitud!");
        }
    }
}
