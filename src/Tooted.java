public class Tooted implements Comparable<Tooted> {

    private String tootenimetus;
    private double hind;
    private int mituTükki;

    public Tooted(String tootenimetus, double hind, int mituTükki) {
        this.tootenimetus = tootenimetus;
        this.hind = hind;
        this.mituTükki = mituTükki;
    }

    public String getTootenimetus() {
        return tootenimetus;
    }

    public void setTootenimetus(String tootenimetus) {
        this.tootenimetus = tootenimetus;
    }

    public double getHind() {
        return hind;
    }

    public void setHind(double hind) {
        this.hind = hind;
    }

    public int getMituTükki() {
        return mituTükki;
    }

    public void setMituTükki(int mituTükki) {
        this.mituTükki = mituTükki;
    }

    @Override
    public String toString() {
        return "Tooted{" +
                "tootenimetus='" + tootenimetus + '\'' +
                ", hind=" + hind +
                ", kogus=" + mituTükki +
                '}';
    }

    @Override
    public int compareTo(Tooted võrreldavToode) {
        return Double.compare(this.hind, võrreldavToode.hind);
    }
}