public class Söögid extends Tooted{
    private int kaal_g;

    public Söögid(String tootenimetus, double hind, int mituTükki, int kaal_g) {
        super(tootenimetus, hind, mituTükki);
        this.kaal_g = kaal_g;
    }

    @Override
    public void tooteMüügiHind() {
        double müügiHind = Math.ceil(getHind() + getHind() * 0.35);
        setHind(müügiHind);
    }

    public int getKaal_g() {
        return kaal_g;
    }

    public void setKaal_g(int kaal_g) {
        this.kaal_g = kaal_g;
    }

    @Override
    public String toString() {
        return "Söögid{" + super.toString() +
                "kaal_g=" + kaal_g +
                '}';
    }
}
