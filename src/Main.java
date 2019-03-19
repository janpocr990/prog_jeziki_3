import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Podjetje hofer = new Podjetje("HOFER TRGOVINA d.o.o.", "SI91043522", "1992414000", true);
        Podjetje henkel = new Podjetje("HENKEL MARIBOR d.o.o.", "SI58665765", "6261752000", true);
        Podjetje spar = new Podjetje("SPAR SLOVENIJA d.o.o.", "SI32156782", "5571693000", true);

        Podjetje [] podjetja = {hofer, henkel, spar};

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

        Racun tretjiRacun = new Racun("SI32156782", "Metka Bizo");

        tretjiRacun.addArtikel("Telečja govedina 1kg", new BigDecimal("8.78"));
        tretjiRacun.addArtikel("Kruh", new BigDecimal("2.89"));
        tretjiRacun.addArtikel("Mleko Špar", new BigDecimal("0.89"));
        tretjiRacun.addArtikel("Mleko Špar", new BigDecimal("0.89"));
        tretjiRacun.addArtikel("Mleko Špar", new BigDecimal("0.89"));

        Podjetje findMe = null;

        for (Podjetje podjetje : podjetja) {
            if (prviRacun.search(podjetje.getDavcna())) {
                findMe = podjetje;
                break;
            }
        }

        if(findMe != null) {
            if (findMe.getDavcniZavezanec()) {
                System.out.println("Podjetje " + findMe.getIme() + " je davčni zavezanec.");
            } else {
                System.out.println("Podjetje " + findMe.getIme() + " ni davčni zavezanec.");
            }
        }

        System.out.println(prviRacun.toString());
        System.out.println(drugiRacun.toString());
        System.out.println(tretjiRacun.toString());

        if(Artikel.checkDigit("3836406651266"))
        {
            System.out.println("Je validen checkdigit!");
        }

        if(!Artikel.checkDigit("8392019283410"))
        {
            System.out.println("Ni validen checkdigit!");
        }
    }
}
