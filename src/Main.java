import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Companies vsaPodjetja = new Companies();
        Invoices vsiRacuni = new Invoices();

        String vsaPodjetjaJSON = Helper.ReadToString("podjetja.txt");
        String vsiRacuniJSON = Helper.ReadToString("racuni.txt");

        if(vsaPodjetjaJSON.length() > 1 && vsiRacuniJSON.length() > 1) {
            vsaPodjetja.fromJSON(vsaPodjetjaJSON);
            vsiRacuni.fromJSON(vsiRacuniJSON);

            for(Podjetje p : vsaPodjetja.getSeznamPodjetij()) {
                System.out.println(p.toString());
            }
            for(Racun r : vsiRacuni.getSeznamRacunov()) {
                System.out.println(r.toString());
            }
        }
        else {
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

            for (int i = 0; i < 50; i++) {
                drugiRacun.addArtikel("ČISTILO ZA STEKLO", new BigDecimal("1.29"), null);
            }

            Racun tretjiRacun = new Racun(null, spar);

            tretjiRacun.addArtikel("Telečja govedina 1kg", new BigDecimal("8.78"), null);
            tretjiRacun.addArtikel("Kruh", new BigDecimal("2.89"), null);
            tretjiRacun.addArtikel("Mleko Špar", new BigDecimal("0.89"), null);
            tretjiRacun.addArtikel("Mleko Špar", new BigDecimal("0.89"), null);
            tretjiRacun.addArtikel("Mleko Špar", new BigDecimal("0.89"), null);

            vsaPodjetja.addPodjetje(hofer);
            vsaPodjetja.addPodjetje(henkel);
            vsaPodjetja.addPodjetje(spar);

            vsiRacuni.addRacun(prviRacun);
            vsiRacuni.addRacun(drugiRacun);
            vsiRacuni.addRacun(tretjiRacun);

            Helper.WriteToFile("racuni.txt", vsiRacuni.toJSON());
            Helper.WriteToFile("podjetja.txt", vsaPodjetja.toJSON());

            System.out.println(prviRacun.toString());
            System.out.println(drugiRacun.toString());
            System.out.println(tretjiRacun.toString());

            if(!Helper.checkDigit("8392019283410")) {
                System.out.println("Ni validen checkdigit!");
            }

            Artikel rubikovaKocka = new Artikel("Rubikova Kocka", new BigDecimal("14.99"), "Canada", 0);

            if(rubikovaKocka.search(rubikovaKocka.getEAN())) {
                System.out.println("EAN za rubikovo kocko je bil najden!");
            }

            System.out.println("Country(" + rubikovaKocka.getIme() + "): " + rubikovaKocka.getDrzava() + ", EAN13: " + rubikovaKocka.getEAN());
            System.out.println("Extracted country from EAN13: " + Helper.getDrzavaFromEAN(rubikovaKocka.getEAN()));

            if(Helper.checkDigit(rubikovaKocka.getEAN())) {
                System.out.println(rubikovaKocka.getIme() + ": Check digit je pravilen.");
            }
            else {
                System.out.println(rubikovaKocka.getIme() + ": Check digit ni pravilen.");
            }
        }

        Podjetje hofer = new Podjetje("HOFER TRGOVINA d.o.o.", "SI91043522", "1992414000", true);
        Racun cetrtiRacun = new Racun("SI72849102", hofer);

        cetrtiRacun.addArtikel("RDEČE VINO 2L", new BigDecimal("1.79"), "Macedonia");
        cetrtiRacun.addArtikel("HRANA ZA MAČKE", new BigDecimal("1.99"), "Poland");
        cetrtiRacun.addArtikel("HRANA ZA MAČKE", new BigDecimal("1.99"), "Poland");
        cetrtiRacun.addArtikel("JAGODE", new BigDecimal("0.01"), "Slovenia", 346);

        System.out.println(cetrtiRacun.getVsiArtikli().getArtikelByIndex(3).getEAN() +
                ": teža iz EAN:" + Helper.GetTezaFromEAN(cetrtiRacun.getVsiArtikli().getArtikelByIndex(3).getEAN()) + "g");

        cetrtiRacun.getVsiArtikli().getArtikelByIndex(3).setTezaGrami(405);

        System.out.println(cetrtiRacun.getVsiArtikli().getArtikelByIndex(3).getEAN() +
                ": teža iz EAN:" + Helper.GetTezaFromEAN(cetrtiRacun.getVsiArtikli().getArtikelByIndex(3).getEAN()) + "g");

        System.out.println(cetrtiRacun.toString());
        cetrtiRacun.setKupon(10, "100419");
        System.out.println(cetrtiRacun.toString());
    }
}
