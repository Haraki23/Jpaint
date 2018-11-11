package model;

import controller.JShapeList;
import model.persistence.ApplicationState;
import view.DrawShapeHandler;

public class ShapeFactory {
    ApplicationState AS;
    private DrawShapeHandler draw;
    public ShapeFactory(ApplicationState AS, DrawShapeHandler draw) {
        this.AS = AS;
        this.draw = draw;
    }

    public void createObject(JShapeList JList) {
        ShapeType shapeType = AS.getActiveShapeType();
        IShape shape;
        switch(shapeType){
            case RECTANGLE:
                shape = new Rectangle(AS);
                JList.registerObserver(shape);
                break;
            case ELLIPSE:
                shape = new Ellipse(AS);
                JList.registerObserver(shape);
                break;
        }
        draw.DrawShapes(draw, JList.getShapeList());
    }

    public void updateShape(JShapeList JList){
        draw.clearcanvas();
        draw.DrawShapes(draw, JList.getShapeList());
    }
}