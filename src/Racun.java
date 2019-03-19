import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Racun implements Searchable{
    private String ID;
    private Timestamp date;
    private Artikli vsiArtikli;
    private String davcna;
    private Podjetje izdajatelj;

    public Racun(String davcna, Podjetje izdajatelj)  {
        date = new Timestamp(System.currentTimeMillis());

        ID = java.util.UUID.randomUUID().toString();

        vsiArtikli = new Artikli();

        this.izdajatelj = izdajatelj;
        this.davcna = davcna;
    }

    public void addArtikel(String ime, BigDecimal cena, String drzava) {
        vsiArtikli.AddArtikel(ime, cena, drzava);
    }

    public String getID() {
        return ID;
    }

    public Timestamp getDate() {
        return date;
    }

    public Artikli getVsiArtikli() {
        return vsiArtikli;
    }

    public Podjetje getIzdajatelj() {
        return izdajatelj;
    }

    public void setIzdajatelj(Podjetje izdajatelj) {
        this.izdajatelj = izdajatelj;
    }

    public String getDavcna() {
        return davcna;
    }

    public void setDavcna(String davcna) {
        this.davcna = davcna;
    }

    @Override
    public String toString() {
        String DDVID = "Brez";

        if(davcna != null){
            DDVID = davcna;
        }

        String out =  "Izdal: " + izdajatelj.toString() +
                "\n" + "ID za DDV: " + izdajatelj.getDavcna() +
                "\n" + "Izdan za DDV: " + DDVID +
                "\nRacun št.: " + ID.toString() +
                ", Datum: " + new SimpleDateFormat("dd.MM.yy HH:mm").format(date) +
                "\n" + vsiArtikli.toString() +
                "\nSkupna Cena Brez DDV: " + vsiArtikli.skupnaCenaBrezDDV() + "€";

        if(davcna == null){
            out += "\nSkupna Cena Z DDV: " + vsiArtikli.skupnaCenaDDV() + "€";
        }
        else{
            out += "\nIzdano davčnemu zavezancu.\n";
        }

        return out;
    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);

            if(hex.length() == 1)
                hexString.append('0');

            hexString.append(hex);
        }

        return hexString.toString();
    }

    public boolean search(String text)
    {
        if(ID.contains(text))
        {
            return true;
        }

        if(date.toString().contains(text))
        {
            return true;
        }

        for(int i = 0; i < vsiArtikli.getSeznamArtiklov().size(); i++)
        {
            if(vsiArtikli.getArtikelByIndex(i).search(text))
            {
                return true;
            }
        }

        if(izdajatelj.search(text))
        {
            return true;
        }

        if(davcna.contains(text))
        {
            return true;
        }

        return false;
    }
}
