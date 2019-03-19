import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


public class Artikli {
    private List<Artikel> seznamArtiklov;

    public Artikli()
    {
        seznamArtiklov = new ArrayList<>();
    }

    public List<Artikel> getSeznamArtiklov() {
        return seznamArtiklov;
    }

    public Artikel getArtikelByIndex(int i) {
        return seznamArtiklov.get(i);
    }

    public void AddArtikel(Artikel dodaj) {
        seznamArtiklov.add(dodaj);
    }

    public void AddArtikel(String ime, BigDecimal cena){
        Artikel dodaj = new Artikel(ime, cena);

        /*boolean add = true;

        for(int i = 0; i < seznamArtiklov.size(); i++)
        {
            if(getArtikelByIndex(i).getIme() == ime) {
                add = false;
                getArtikelByIndex(i).setKolicina(getArtikelByIndex(i).getKolicina() + 1);
            }
        }
*/

            AddArtikel(dodaj);

    }

    public int getNumberOfArtikels(String ime) {
        int count = 0;

        for(int i = 0; i < seznamArtiklov.size(); i++)
        {
            if(getArtikelByIndex(i).getIme() == ime)
            {
                count = getArtikelByIndex(i).getKolicina();
                break;
            }
        }

        return count;
    }

    public BigDecimal skupnaCenaBrezDDV()
    {
        BigDecimal vseSkupaj = new BigDecimal(0);

        for(int i = 0; i < seznamArtiklov.size(); i++) {
            int numOfArtikli = getNumberOfArtikels(getArtikelByIndex(i).getIme());
            BigDecimal skupnaCena = new BigDecimal(numOfArtikli);
            skupnaCena = skupnaCena.multiply(getArtikelByIndex(i).getCena());

            vseSkupaj = vseSkupaj.add(skupnaCena);
        }

        return vseSkupaj;
}

    public BigDecimal skupnaCenaDDV()
    {
        BigDecimal vseSkupaj = new BigDecimal(0);

        for(int i = 0; i < seznamArtiklov.size(); i++) {
            int numOfArtikli = getNumberOfArtikels(getArtikelByIndex(i).getIme());
            BigDecimal skupnaCena = new BigDecimal(numOfArtikli);
            skupnaCena = skupnaCena.multiply(getArtikelByIndex(i).getCena());

            vseSkupaj = vseSkupaj.add(skupnaCena);
        }

        vseSkupaj = vseSkupaj.multiply(Artikel.davncaStopnja);
        vseSkupaj = vseSkupaj.setScale(2, RoundingMode.CEILING);

        return vseSkupaj;
    }

    @Override
    public String toString() {
        String ret = "";

        for(Artikel _artikel : seznamArtiklov)
        {
            int numOfArtikli = getNumberOfArtikels(_artikel.getIme());
            BigDecimal skupnaCena = new BigDecimal(numOfArtikli);
            skupnaCena = skupnaCena.multiply(_artikel.getCena());

            ret += _artikel.getIme() + "\t" + skupnaCena.toString() + "€\n";

        }

        return ret;
    }
}
