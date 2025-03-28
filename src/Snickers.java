public class Snickers extends Tooted{

    private int kaal_g;

    public Snickers(String tootenimetus, double hind, int mituTükki, int kaal_g) {
        super(tootenimetus, hind, mituTükki);
        this.kaal_g = kaal_g;
    }

    public int getKaal_g() {
        return kaal_g;
    }

    public void setKaal_g(int kaal_g) {
        this.kaal_g = kaal_g;
    }
}
