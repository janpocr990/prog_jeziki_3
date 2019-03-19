import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class Racun implements Searchable{
    private String ID;
    private Timestamp date;
    private Artikli vsiArtikli;
    private String izdajatelj;
    private String davcna;

    public Racun(String davcna_st, String izdajatelj_rc) throws NoSuchAlgorithmException {
        date = new Timestamp(System.currentTimeMillis());

        String HashData = Long.toString(System.nanoTime()) + date.toString();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(HashData.toString().getBytes(StandardCharsets.UTF_8));
        ID = bytesToHex(hash);

        vsiArtikli = new Artikli();

        davcna = davcna_st;
        izdajatelj = izdajatelj_rc;
    }

    public void addArtikel(String ime, BigDecimal cena)
    {
        vsiArtikli.AddArtikel(ime, cena);
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

    public String getIzdajatelj() {
        return izdajatelj;
    }

    public void setIzdajatelj(String izdajatelj) {
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
        return "ID za DDV: " + davcna +
                "\nIzdajatelj: " + izdajatelj +
                "\n{" +
                "\nRacun št.: " + ID.toString() +
                ", Datum: " + new SimpleDateFormat("dd.MM.yy HH:mm").format(date) +
                "\n" + vsiArtikli.toString() +
                "\nSkupna Cena Brez DDV: " + vsiArtikli.skupnaCenaBrezDDV() + "€" +
                "\nSkupna Cena Z DDV: " + vsiArtikli.skupnaCenaDDV() + "€" +
                "\n}";
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

        if(izdajatelj.contains(text))
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
