import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Companies implements JsonSupport{

    private List<Podjetje> seznamPodjetij;

    public Companies() {
        this.seznamPodjetij = new ArrayList<>();
    }

    public void addPodjetje(Podjetje p){
        seznamPodjetij.add(p);
    }

    public List<Podjetje> getSeznamPodjetij() {
        return seznamPodjetij;
    }

    @Override
    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        String out = gson.toJson(seznamPodjetij);
        return out;
    }

    @Override
    public void fromJSON(String JSON) {
        Gson gson = new GsonBuilder().create();

        Type collectionType = new TypeToken<Collection<Podjetje>>(){}.getType();
        Collection<Podjetje> copy = gson.fromJson(JSON, collectionType);

        seznamPodjetij = new ArrayList(copy);
    }
}
