package controller;
import java.util.*;

import model.IShape;
import model.persistence.IJShape;

public class SelectedJShapeList implements IJShape {
    Map <UUID, IShape> JshapesMap;
    ArrayList<IShape> Jshapes;
    public SelectedJShapeList(){
        this.JshapesMap = new HashMap<>();
        this.Jshapes = new ArrayList<>();
    }

    @Override
    public void registerObserver(IShape shape) {JshapesMap.put(shape.getShape_ID(),shape);}

    @Override
    public void removeObserver(IShape shape) {Jshapes.remove(shape);}

    public ArrayList <IShape> getShapeList() {
        ArrayList<IShape> temp = new ArrayList<IShape>(JshapesMap.values());
        Jshapes = temp;
        return Jshapes;
    }

    public void clear_list(){
        JshapesMap.clear();
        Jshapes.clear();
    }
}
