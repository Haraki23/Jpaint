package model;

import model.persistence.ApplicationState;

public class TriangleFactory implements IShapeFactory{
    ApplicationState AS;

    public TriangleFactory(ApplicationState AS){
        this.AS = AS;
    }
    @Override
    public IShape createShape() {
        return new Triangle(AS);
    }
}