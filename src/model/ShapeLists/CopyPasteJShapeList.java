package model.ShapeLists;
import java.util.ArrayList;

import model.Interfaces.IShape;
import model.Interfaces.IJShape;

public class CopyPasteJShapeList implements IJShape {
    ArrayList <IShape> Jshapes;
    public CopyPasteJShapeList(){
        this.Jshapes = new ArrayList<>();
    }
    @Override
    public void registerObserver(IShape shape) {
        Jshapes.add(shape);
    }

    @Override
    public void removeObserver(IShape shape) {
        Jshapes.remove(shape);
    }

    public ArrayList <IShape> getShapeList() {return Jshapes;}

    public void clear_list(){Jshapes.clear();}

}