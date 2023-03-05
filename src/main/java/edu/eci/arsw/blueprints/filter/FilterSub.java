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
@Qualifier("Sub")
public class FilterSub implements Filters {

    @Override
    public void Blueprint(Blueprint bp) throws BlueprintNotFoundException {
        List<Point> points = new ArrayList<Point>(bp.getPoints());
        List<Point> pointsFilter = new ArrayList<Point>();
        for(int i = 0; i<points.size();i++){
            if(i%2 == 1){
                pointsFilter.add(points.get(i));
            }
        }
        bp.setPoints(pointsFilter);
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
