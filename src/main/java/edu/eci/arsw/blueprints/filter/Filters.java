package edu.eci.arsw.blueprints.filter;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;

import java.util.Set;

public interface Filters {

    /**
     * Filtra los puntos de un blueprint
     * @param bp
     * @throws BlueprintNotFoundException
     */
    public void Blueprint(Blueprint bp) throws BlueprintNotFoundException;

    /**
     * Filtra un arreglo de blueprints
     * @param blueprints
     * @throws BlueprintPersistenceException
     * @throws BlueprintNotFoundException
     */
    public void Blueprints(Set<Blueprint> blueprints) throws BlueprintPersistenceException, BlueprintNotFoundException;

    /**
     * Filtra un arreglo de blueprints pero solo del autor escogido
     * @param author
     * @param blueprints
     * @throws BlueprintNotFoundException
     */
    public void filterByAuthor(String author, Set<Blueprint> blueprints) throws BlueprintNotFoundException;
}
