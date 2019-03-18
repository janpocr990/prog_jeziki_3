import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Racun prviRacun = new Racun();
        prviRacun.addArtikel("Žemlja", new BigDecimal("0.49"));
        prviRacun.addArtikel("Žemlja", new BigDecimal("0.49"));
        prviRacun.addArtikel("Žemlja", new BigDecimal("0.49"));
        prviRacun.addArtikel("Coca-Cola", new BigDecimal("0.69"));
        prviRacun.addArtikel("Kruh", new BigDecimal("1.22"));
        prviRacun.addArtikel("Mleko", new BigDecimal("1.52"));

        System.out.println(prviRacun.toString());
    }
}
