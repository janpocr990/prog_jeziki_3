import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Artikli implements JsonSupport{
    @Expose
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

    public void AddArtikel(String ime, BigDecimal cena, String drzava){
        Artikel dodaj = new Artikel(ime, cena, drzava, 0);
        AddArtikel(dodaj);
    }

    public void AddArtikel(String ime, BigDecimal cena, String drzava, int teza){
        Artikel dodaj = new Artikel(ime, cena, drzava, teza);
        AddArtikel(dodaj);
    }

    public int getTezaOfArtikel(String ime) {
        int count = 0;

        for(int i = 0; i < seznamArtiklov.size(); i++)
        {
            if(getArtikelByIndex(i).getIme() == ime)
            {
                count = getArtikelByIndex(i).getTeza();
                break;
            }
        }

        return count;
    }

    public BigDecimal skupnaCenaBrezDDV(int procent_popust, Timestamp veljaDo)
    {
        BigDecimal vseSkupaj = new BigDecimal(0);

        for(int i = 0; i < seznamArtiklov.size(); i++) {
            int tezaOfArt = getTezaOfArtikel(getArtikelByIndex(i).getIme());

            if(tezaOfArt == 0) {
                tezaOfArt = 1;
            }

            BigDecimal skupnaCena = new BigDecimal(tezaOfArt);
            skupnaCena = skupnaCena.multiply(getArtikelByIndex(i).getCena());

            vseSkupaj = vseSkupaj.add(skupnaCena);
        }

        if(procent_popust > 0 && veljaDo != null){
            Timestamp currTime = new Timestamp(System.currentTimeMillis());
            if(currTime.before(veljaDo)) {
                BigDecimal scale = new BigDecimal(Integer.toString(procent_popust));
                scale = scale.divide(new BigDecimal("100"));
                scale = vseSkupaj.multiply(scale);

                vseSkupaj = vseSkupaj.subtract(scale);
                vseSkupaj = vseSkupaj.setScale(2, RoundingMode.CEILING);
            }
        }

        return vseSkupaj;
}

    public BigDecimal skupnaCenaDDV(int procent_popust, Timestamp veljaDo)
    {
        BigDecimal vseSkupaj = new BigDecimal(0);

        for(int i = 0; i < seznamArtiklov.size(); i++) {
            int tezaOfArt = getTezaOfArtikel(getArtikelByIndex(i).getIme());

            if(tezaOfArt == 0) {
                tezaOfArt = 1;
            }

            BigDecimal skupnaCena = new BigDecimal(tezaOfArt);
            skupnaCena = skupnaCena.multiply(getArtikelByIndex(i).getCena());

            vseSkupaj = vseSkupaj.add(skupnaCena);
        }

        vseSkupaj = vseSkupaj.multiply(Helper.davncaStopnja);
        vseSkupaj = vseSkupaj.setScale(2, RoundingMode.CEILING);

        if(procent_popust > 0 && veljaDo != null){
            Timestamp currTime = new Timestamp(System.currentTimeMillis());
            if(currTime.before(veljaDo)) {
                BigDecimal scale = new BigDecimal(Integer.toString(procent_popust));
                scale = scale.divide(new BigDecimal("100"));
                scale = vseSkupaj.multiply(scale);

                vseSkupaj = vseSkupaj.subtract(scale);
                vseSkupaj = vseSkupaj.setScale(2, RoundingMode.CEILING);
            }
        }

        return vseSkupaj;
    }

    @Override
    public String toString() {
        String ret = "";

        for(Artikel _artikel : seznamArtiklov)
        {
            int tezaOfArt = getTezaOfArtikel(_artikel.getIme());

            if(tezaOfArt == 0) {
                tezaOfArt = 1;
            }

            BigDecimal skupnaCena = new BigDecimal(tezaOfArt);
            skupnaCena = skupnaCena.multiply(_artikel.getCena());

            ret += _artikel.getIme() + "\t" + skupnaCena.toString() + "€\n";

        }

        return ret;
    }

    @Override
    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        String out = gson.toJson(seznamArtiklov);
        return out;
    }

    @Override
    public void fromJSON(String JSON) {
        Gson gson = new GsonBuilder().create();

        Type collectionType = new TypeToken<Collection<Artikel>>(){}.getType();
        Collection<Artikel> copy = gson.fromJson(JSON, collectionType);

        seznamArtiklov = new ArrayList(copy);
    }
}
