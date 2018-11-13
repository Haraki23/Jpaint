package model;

import model.persistence.ApplicationState;

public class RectangleFactory implements IShapeFactory{
    ApplicationState AS;

    public RectangleFactory(ApplicationState AS){
        this.AS = AS;
    }
    @Override
    public IShape createShape() {
        return new Rectangle(AS);
    }
}