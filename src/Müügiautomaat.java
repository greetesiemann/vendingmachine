import java.util.ArrayList;
import java.util.List;

public class Müügiautomaat {

    private String automaadinimi;
    private List<Tooted> tooted;

    public Müügiautomaat(String automaadinimi) {
        this.automaadinimi = automaadinimi;
        this.tooted = new ArrayList<>();
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


}