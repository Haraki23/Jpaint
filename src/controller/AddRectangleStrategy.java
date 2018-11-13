package controller;

import model.RectangleFactory;
import model.ShapeType;
import model.persistence.ApplicationState;

public class AddRectangleStrategy implements IShapeAdder {

    private RectangleFactory rectangle;
    private JShapeList JList;
    private ApplicationState AS;

    public AddRectangleStrategy(JShapeList JList, ApplicationState AS) {
        this.rectangle = new RectangleFactory(AS);
        this.JList = JList;
        this.AS = AS;
    }

    public void ShapeAdder(ShapeType shapeType) {
        JList.registerObserver(rectangle.createShape());
    }

}