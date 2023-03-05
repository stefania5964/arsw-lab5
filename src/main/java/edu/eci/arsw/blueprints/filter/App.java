package edu.eci.arsw.blueprints.filter;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class App {
    @Autowired
    @Qualifier("filterR")
    Filters filter;



    public void Blueprint(Blueprint bp) throws BlueprintNotFoundException {
        filter.Blueprint(bp);
    }

    public void Blueprints(Set<Blueprint> blueprints) throws BlueprintNotFoundException, BlueprintPersistenceException {
        filter.Blueprints(blueprints);

    }
}
