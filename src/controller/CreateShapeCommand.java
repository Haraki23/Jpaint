package controller;

import model.ShapeFactory;


public class CreateShapeCommand implements ICommand {
    private ShapeFactory shape;
    private JPoint Pt;
    private JShapeList Jshape;
    public CreateShapeCommand(ShapeFactory shape, JPoint Pt, JShapeList Jshape) {
        this.shape = shape;
        this.Jshape = Jshape;
        this.Pt = Pt;
    }

    @Override
    public void run(){shape.createObject(Jshape);}

}
