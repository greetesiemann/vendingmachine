import java.util.ArrayList;
import java.util.List;

public class Müügiautomaat {

    private String automaadinimi;
    private List<Tooted> tooted;
    private double rahaAutomaadis;

    public Müügiautomaat(String automaadinimi, double rahaAutomaadis) {
        this.automaadinimi = automaadinimi;
        this.tooted = new ArrayList<>();
        this.rahaAutomaadis = 0;
    }

    public String getAutomaadinimi() {
        return automaadinimi;
    }

    public void setAutomaadinimi(String automaadinimi) {
        this.automaadinimi = automaadinimi;
    }

    public double getRahaAutomaadis() {
        return rahaAutomaadis;
    }

    public void setRahaAutomaadis(double rahaAutomaadis) {
        this.rahaAutomaadis = rahaAutomaadis;
    }

    public void lisaToode(Tooted toode) {
        this.tooted.add(toode);
    }

    public void eemaldaToode(Tooted toode) {
        this.tooted.remove(toode);
    }

    public int mituTükkiOnToodet(Tooted toode) {
       return toode.getMituTükki();
    }

    public void lisaRaha(Tooted toode) {
        this.rahaAutomaadis += toode.getHind();
    }
}