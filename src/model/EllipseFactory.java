package model;

import model.persistence.ApplicationState;

public class EllipseFactory implements IShapeFactory{
    ApplicationState AS;

    public EllipseFactory(ApplicationState AS){
        this.AS = AS;
    }
    @Override
    public IShape createShape() {
        return new Ellipse(AS);
    }
}