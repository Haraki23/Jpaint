package model;

import controller.JShapeList;
import model.persistence.ApplicationState;
import view.DrawShapeHandler;

public class ShapeFactory {
    ApplicationState AS;
    private DrawShapeHandler draw;
    public ShapeFactory(ApplicationState AS, DrawShapeHandler draw) { // Also take in the ShapeList Class here
        this.AS = AS;
        this.draw = draw;
    }

    public void createObject(JShapeList JList) {
        ShapeType shapeName = AS.getActiveShapeType();
        IShape shape;
        switch(shapeName){
            case RECTANGLE:
                System.out.println("Were creating a new rectangle here");
                shape = new Rectangle(AS);
                JList.registerObserver(shape);
                break;
        }
        //draw.clearcanvas();
        draw.DrawShapes(draw, JList.getShapeList());
    }

    public void updateShape(JShapeList JList){
        draw.clearcanvas();
        draw.DrawShapes(draw, JList.getShapeList());
    }
}