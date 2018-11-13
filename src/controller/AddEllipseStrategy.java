package controller;

import model.*;
import model.persistence.ApplicationState;

public class AddEllipseStrategy implements IShapeAdder {

    private EllipseFactory ellipse;
    private JShapeList JList;
    private ApplicationState AS;

    public AddEllipseStrategy(JShapeList JList, ApplicationState AS) {
        this.ellipse = new EllipseFactory(AS);
        this.JList = JList;
        this.AS = AS;
    }

    public void ShapeAdder(ShapeType shapeType) {

        JList.registerObserver(ellipse.createShape());
    }

}
