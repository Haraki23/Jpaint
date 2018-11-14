package model.Factories;

import model.Shapes.Ellipse;
import model.Interfaces.IShape;
import model.Interfaces.IShapeFactory;
import model.Persistence.ApplicationState;

public class EllipseFactory implements IShapeFactory {
    ApplicationState AS;

    public EllipseFactory(ApplicationState AS){
        this.AS = AS;
    }
    @Override
    public IShape createShape() {
        return new Ellipse(AS);
    }
}