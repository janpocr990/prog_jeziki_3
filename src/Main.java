import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Podjetje hofer = new Podjetje("HOFER TRGOVINA D.O.O.", "SI91043522", "1992414000", true);

        Racun prviRacun = new Racun("SI91043522", "ID18");

        prviRacun.addArtikel("RDEČE VINO 2L", new BigDecimal("1.79"));
        prviRacun.addArtikel("HRANA ZA MAČKE", new BigDecimal("1.99"));
        prviRacun.addArtikel("HRANA ZA MAČKE", new BigDecimal("1.99"));
        prviRacun.addArtikel("ČOK. KINDER SURPRISE", new BigDecimal("0.81"));
        prviRacun.addArtikel("ČOK. KINDER SURPRISE", new BigDecimal("0.81"));
        prviRacun.addArtikel("ČOK. KINDER SURPRISE", new BigDecimal("0.81"));
        prviRacun.addArtikel("ŽEMLJE", new BigDecimal("0.60"));
        prviRacun.addArtikel("JAGODE", new BigDecimal("3.58"));

        Racun drugiRacun = new Racun("SI58665765", "Marjan Holcer");

        for(int i = 0; i < 50; i++) {
            drugiRacun.addArtikel("ČISTILO ZA STEKLO", new BigDecimal("1.29"));
        }

        System.out.println(prviRacun.toString());
        System.out.println(drugiRacun.toString());
    }
}
