import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Invoices implements JsonSupport{

    private List<Racun> seznamRacunov;

    public Invoices() {
        this.seznamRacunov = new ArrayList<>();
    }

    public void addRacun(Racun r){
        seznamRacunov.add(r);
    }

    public List<Racun> getSeznamRacunov() {
        return seznamRacunov;
    }

    @Override
    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        String out = gson.toJson(seznamRacunov);
        return out;
    }

    @Override
    public void fromJSON(String JSON) {
        Gson gson = new GsonBuilder().create();

        Type collectionType = new TypeToken<Collection<Racun>>(){}.getType();
        Collection<Racun> copy = gson.fromJson(JSON, collectionType);

        seznamRacunov = new ArrayList(copy);
    }
}
