package controller.Strategies;

import controller.Interfaces.IShapeAdder;
import model.Factories.EllipseFactory;
import model.ShapeLists.JShapeList;
import model.*;
import model.Persistence.ApplicationState;

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
