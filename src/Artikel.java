import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

import com.google.gson.annotations.Expose;

public class Artikel implements Searchable{
    private String ime;
    private BigDecimal cena;
    private String drzava;
    private String oddelek;
    private String id_izdelka;

    //za list
    private String EAN;
    //ce je 0 nima teze in je konstantna cena, ce je nad 0 pomeni da se poracuna cena po tezi
    private int tezaGrami;

    public Artikel(String ime, BigDecimal cena, String drzava, int teza) {
        this.ime = ime;
        this.cena = cena;
        this.tezaGrami = teza;

        if(teza != 0) {
            this.drzava = drzava;
            this.oddelek = Integer.toString(ThreadLocalRandom.current().nextInt(200, 300));
            this.id_izdelka = Integer.toString(ThreadLocalRandom.current().nextInt(1000, 10000));

            this.setEANTeza();
        }
        else {
            if (drzava != null) {
                this.drzava = drzava;
                this.setEAN(drzava);
            } else {
                this.setEAN();
                this.drzava = Helper.getDrzavaFromEAN(getEAN());
            }
        }
    }

    public boolean search(String text)
    {
        if(ime.contains(text)) {
            return true;
        }

        if(cena.toString().contains(text)) {
            return true;
        }

        if(getEAN().contains(text)) {
            return true;
        }

        if(Integer.toString(tezaGrami).contains(text)) {
            return true;
        }

        return false;
    }

    public int getTezaGrami() {
        return tezaGrami;
    }

    public void setTezaGrami(int tezaGrami) {
        this.tezaGrami = tezaGrami;
        this.setEANTeza();
    }

    public String getDrzava() {
        return drzava;
    }

    public String getIme() {
        return ime;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public int getTeza() {
        return tezaGrami;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public String getEAN() {
        return EAN;
    }

    public void setEAN(){
        EAN = Helper.GenerateRandomEAN(null);
    }

    public void setEAN(String drzava){
        EAN = Helper.GenerateRandomEAN(drzava);
    }

    public void setEANTeza(){
        EAN = Helper.GenerateEANTezaCena(oddelek, id_izdelka, this.tezaGrami);
    }
}
