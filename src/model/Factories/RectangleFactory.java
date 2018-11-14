package model.Factories;

import model.Shapes.Rectangle;
import model.Interfaces.IShape;
import model.Interfaces.IShapeFactory;
import model.Persistence.ApplicationState;

public class RectangleFactory implements IShapeFactory {
    ApplicationState AS;

    public RectangleFactory(ApplicationState AS){
        this.AS = AS;
    }
    @Override
    public IShape createShape() {
        return new Rectangle(AS);
    }
}