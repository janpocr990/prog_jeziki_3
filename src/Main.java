import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Podjetje hofer = new Podjetje("HOFER TRGOVINA d.o.o.", "SI91043522", "1992414000", true);
        Podjetje henkel = new Podjetje("HENKEL MARIBOR d.o.o.", "SI58665765", "6261752000", true);
        Podjetje spar = new Podjetje("SPAR SLOVENIJA d.o.o.", "SI32156782", "5571693000", true);

        Racun prviRacun = new Racun("SI72849102", hofer);

        prviRacun.addArtikel("RDEČE VINO 2L", new BigDecimal("1.79"), null);
        prviRacun.addArtikel("HRANA ZA MAČKE", new BigDecimal("1.99"), null);
        prviRacun.addArtikel("HRANA ZA MAČKE", new BigDecimal("1.99"), null);
        prviRacun.addArtikel("ČOK. KINDER SURPRISE", new BigDecimal("0.81"), null);
        prviRacun.addArtikel("ČOK. KINDER SURPRISE", new BigDecimal("0.81"), null);
        prviRacun.addArtikel("ČOK. KINDER SURPRISE", new BigDecimal("0.81"), null);
        prviRacun.addArtikel("ŽEMLJE", new BigDecimal("0.60"), null);
        prviRacun.addArtikel("JAGODE", new BigDecimal("3.58"), null);

        Racun drugiRacun = new Racun("SI72849102", henkel);

        for(int i = 0; i < 50; i++) {
            drugiRacun.addArtikel("ČISTILO ZA STEKLO", new BigDecimal("1.29"), null);
        }

        Racun tretjiRacun = new Racun(null, spar);

        tretjiRacun.addArtikel("Telečja govedina 1kg", new BigDecimal("8.78"), null);
        tretjiRacun.addArtikel("Kruh", new BigDecimal("2.89"), null);
        tretjiRacun.addArtikel("Mleko Špar", new BigDecimal("0.89"), null);
        tretjiRacun.addArtikel("Mleko Špar", new BigDecimal("0.89"), null);
        tretjiRacun.addArtikel("Mleko Špar", new BigDecimal("0.89"), null);

        System.out.println(prviRacun.toString());
        System.out.println(drugiRacun.toString());
        System.out.println(tretjiRacun.toString());

        if(!Artikel.checkDigit("8392019283410")) {
            System.out.println("Ni validen checkdigit!");
        }

        Artikel rubikovaKocka = new Artikel("Rubikova Kocka", new BigDecimal("14.99"), "Canada");

        if(rubikovaKocka.search(rubikovaKocka.getEAN())) {
            System.out.println("EAN za rubikovo kocko je bil najden!");
        }

        System.out.println("Country(" + rubikovaKocka.getIme() + "): " + rubikovaKocka.getDrzava() + ", EAN13: " + rubikovaKocka.getEAN());
        System.out.println("Extracted country from EAN13: " + Artikel.getDrzavaFromEAN(rubikovaKocka.getEAN()));

        if(Artikel.checkDigit(rubikovaKocka.getEAN())) {
            System.out.println(rubikovaKocka.getIme() + ": Check digit je pravilen.");
        }
        else {
            System.out.println(rubikovaKocka.getIme() + ": Check digit ni pravilen.");
        }
    }
}
