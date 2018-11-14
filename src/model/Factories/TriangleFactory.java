package model.Factories;

import model.Shapes.Triangle;
import model.Interfaces.IShape;
import model.Interfaces.IShapeFactory;
import model.Persistence.ApplicationState;

public class TriangleFactory implements IShapeFactory {
    ApplicationState AS;

    public TriangleFactory(ApplicationState AS){
        this.AS = AS;
    }
    @Override
    public IShape createShape() {
        return new Triangle(AS);
    }
}