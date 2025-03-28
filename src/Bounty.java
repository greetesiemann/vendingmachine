public class Bounty extends Tooted{

    private int kaal_g;

    public Bounty(String tootenimetus, double hind, int mituTükki, int kaal_g) {
        super(tootenimetus, hind, mituTükki);
        this.kaal_g = kaal_g;
    }
}
