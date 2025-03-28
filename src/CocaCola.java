public class CocaCola extends Tooted{

    private int maht_ml;

    public CocaCola(String tootenimetus, double hind, int mituTükki, int maht_ml) {
        super(tootenimetus, hind, mituTükki);
        this.maht_ml = maht_ml;
    }
}
