package controller;

import model.ShapeFactory;


public class CreateShapeCommand implements ICommand, IUndoable {
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

    @Override
    public void undo() {
        Jshape.removeLast();
    }

    @Override
    public void redo() {

        Jshape.addLast();

    }
}
