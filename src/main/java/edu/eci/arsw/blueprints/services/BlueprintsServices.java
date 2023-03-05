package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.filter.Filters;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class BlueprintsServices {

    @Autowired
    @Qualifier("Memory")
    BlueprintsPersistence bpp;
    Filters filter;

    /**
     * Add new Blueprint in the class InMemoryBluePrintPersistence
     * @param bp
     * @throws BlueprintPersistenceException
     */
    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        bpp.saveBlueprint(bp);
    }

    /**
     * Get the Set of Blueprints
     * @return
     * @throws BlueprintNotFoundException
     * @throws BlueprintPersistenceException
     */
    public Set<Blueprint> getAllBlueprints() throws BlueprintNotFoundException, BlueprintPersistenceException {
        return bpp.getBluePrints();
    }

    /**
     * the blueprint of the given name created by the given author
     * @param author
     * @param name
     * @return
     * @throws BlueprintNotFoundException
     */
    public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException {
        return bpp.getBlueprint(author, name);
    }
    /**
     * all the blueprints of the given author
     * @param author
     * @return
     * @throws BlueprintNotFoundException
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException, BlueprintPersistenceException{
        return bpp.getBlueprintsByAuthor(author);
    }


    public void updateBlueprint(String author, String bpname, List<Point> points) throws BlueprintNotFoundException {
        Blueprint bp = getBlueprint(author, bpname);
        bp.setPoints(points);
    }
    public void setBlueprint(Blueprint bp, String author, String bprint) throws BlueprintNotFoundException {
        bpp.getBlueprint(author, bprint).setAuthor(bp.getAuthor());
        bpp.getBlueprint(author, bprint).setBprint(bp.getName());
    }

}