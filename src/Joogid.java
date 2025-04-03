public class Joogid extends Tooted {
    private int maht_ml;

    public Joogid(String tootenimetus, double hind, int mituTükki, int maht_ml) {
        super(tootenimetus, hind, mituTükki);
        this.maht_ml = maht_ml;
    }

    @Override
    public void tooteMüügiHind() {
        double müügiHind = Math.ceil(getHind() + getHind() * 0.55);
        setHind(müügiHind);
    }

    public int getMaht_ml() {
        return maht_ml;
    }

    public void setMaht_ml(int maht_ml) {
        this.maht_ml = maht_ml;
    }

    @Override
    public String toString() {
        return "Joogid{" + super.toString() +
                "maht_ml=" + maht_ml +
                '}';
    }
}
