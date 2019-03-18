import java.util.regex.Pattern;

public class Podjetje implements Searchable{
    private String ime;
    private String davcna;
    private String maticnaStevilka;
    private boolean davcniZavezanec;

    public Podjetje(String ime, String davcna, String maticnaStevilka, boolean davcniZavezanec) {
        this.ime = ime;
        this.davcna = davcna;
        this.maticnaStevilka = maticnaStevilka;
        this.davcniZavezanec = davcniZavezanec;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getDavcna() {
        return davcna;
    }

    public void setDavcna(String davcna) {
        this.davcna = davcna;
    }

    public String getMaticnaStevilka() {
        return maticnaStevilka;
    }

    public void setMaticnaStevilka(String maticnaStevilka) {
        this.maticnaStevilka = maticnaStevilka;
    }

    public boolean getDavcniZavezanec() {
        return davcniZavezanec;
    }

    public void setDavcniZavezanec(boolean davcniZavezanec) {
        this.davcniZavezanec = davcniZavezanec;
    }

    public boolean search(String text)
    {
        if(ime.contains(text))
        {
            return true;
        }

        if(davcna.contains(text))
        {
            return true;
        }

        if(maticnaStevilka.contains(text))
        {
            return true;
        }

        return false;
    }
}
