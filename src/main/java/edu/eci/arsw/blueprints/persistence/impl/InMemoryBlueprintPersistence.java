package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author hcadavid
 */
@Component
@Qualifier("Memory")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final Map<Tuple<String,String>,Blueprint> blueprints=new HashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        for(int i=0;i<2;i++){
            int A =(int)(Math.random()*140);
            int T =(int)(Math.random()*140);
            Point[] pts=new Point[]{new Point(A, T),new Point(T, T)};
            Blueprint bp1=new Blueprint("gruñon", "plano"+(i+1),pts);
            blueprints.put(new Tuple<>(bp1.getAuthor(),bp1.getName()), bp1);
        }
        Point[] pt=new Point[]{new Point(140, 140),new Point(115, 115)};
        Blueprint bp2=new Blueprint("copito", "plano3",pt);
        blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);

    }



    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (this.blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            this.blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        System.out.println(blueprints.size());
        return blueprints.get(new Tuple<>(author, bprintname));
    }

    @Override
    public Set<Blueprint> getBluePrints() throws BlueprintPersistenceException, BlueprintNotFoundException {
        Set<Blueprint> bprints = new HashSet<>();
        for(Tuple<String,String> tuple: this.blueprints.keySet()){
            bprints.add(blueprints.get(tuple));
        }
        return bprints;
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        Set<Blueprint> bprints = new HashSet<>();
        for(Tuple<String,String> tuple: this.blueprints.keySet()){
            if(tuple.o1.equals(author)){
                bprints.add(blueprints.get(tuple));
            }
        }
        return bprints;
    }
}
