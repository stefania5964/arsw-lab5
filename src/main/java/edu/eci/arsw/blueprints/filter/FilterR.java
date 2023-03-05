package edu.eci.arsw.blueprints.filter;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@Qualifier("FilterR")
public class FilterR implements Filters {

    /**
     * Elimina todos los puntos repetidos
     * @param bp
     * @param point
     */
    public void review(Blueprint bp, Point point){
        List<Point> points = new ArrayList<Point>(bp.getPoints());
        for(int i = 0; i<=points.size()-1;i++){
            if(point.equals(points.get(i))){
                points.remove(i);
            }
        }
        points.add(point);
        bp.setPoints(points);
    }


    @Override
    public void Blueprint(Blueprint bp) throws BlueprintNotFoundException {
        for(Point point:bp.getPoints()){
            review(bp,point);
        }
    }


    @Override
    public void Blueprints(Set<Blueprint> blueprints) throws BlueprintPersistenceException, BlueprintNotFoundException {
        for(Blueprint print: blueprints){
            Blueprint(print);
        }
    }


    @Override
    public void filterByAuthor(String author,Set<Blueprint> blueprints) throws BlueprintNotFoundException {
        for(Blueprint print: blueprints){
            if(print.getAuthor().equals(author)){
                Blueprint(print);
            }
        }
    }
}