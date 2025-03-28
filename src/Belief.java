public class Belief extends Tooted{

    private int maht_ml;

    public Belief(String tootenimetus, double hind, int mituTükki, int maht_ml) {
        super(tootenimetus, hind, mituTükki);
        this.maht_ml = maht_ml;
    }

    public int getMaht_ml() {
        return maht_ml;
    }

    public void setMaht_ml(int maht_ml) {
        this.maht_ml = maht_ml;
    }
}