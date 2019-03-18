import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.UUID;

public class Racun {
    private String ID;
    private Timestamp date;
    private Artikli vsiArtikli;

    public Racun() throws NoSuchAlgorithmException {
        date = new Timestamp(System.currentTimeMillis());

        String HashData = Long.toString(System.nanoTime()) + date.toString();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(HashData.toString().getBytes(StandardCharsets.UTF_8));
        ID = bytesToHex(hash);

        vsiArtikli = new Artikli();
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

    @Override
    public String toString() {
        return "Racun{" +
                "ID=" + ID.toString() +
                ", date=" + date +
                ", vsiArtikli=" + vsiArtikli.toString() +
                ", Skupna Cena Brez DDV=" + vsiArtikli.skupnaCenaBrezDDV() + "€" +
                ", Skupna Cena Z DDV=" + vsiArtikli.skupnaCenaDDV() + "€" +
                '}';
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
}
