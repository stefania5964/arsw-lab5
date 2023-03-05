package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;

import java.util.Set;

/**
 *
 * @author hcadavid
 */
public interface BlueprintsPersistence {

    /**
     * Guarda el Blueprint
     * @param bp blueprint
     * @throws BlueprintPersistenceException
     */
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException;

    /**
     * Retorna el blueprint
     * @param author
     * @param bprintname
     * @return
     * @throws BlueprintNotFoundException
     */
    public Blueprint getBlueprint(String author,String bprintname) throws BlueprintNotFoundException;

    /**
     * Retorna los Set de Blueprints
     * @return
     * @throws BlueprintPersistenceException
     * @throws BlueprintNotFoundException
     */
    public Set<Blueprint> getBluePrints() throws BlueprintPersistenceException, BlueprintNotFoundException;

    /**
     * Retorna los Blueprints creados por un autor
     * @param author
     * @return
     * @throws BlueprintNotFoundException si el autor no existe
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException;

}