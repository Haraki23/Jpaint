package view;

import controller.*;
import model.ShapeFactory;
import model.persistence.ApplicationState;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class User_Mouse_Handler extends MouseAdapter {
    private ICommand command = null;
    private ShapeFactory shape;
    private ApplicationState AS;
    public User_Mouse_Handler(ShapeFactory shape, ApplicationState AS) {
        this.shape = shape;
        this.AS = AS;
    }

    //Create rect through a command object being instantiated in here
    @Override
    public void mousePressed(MouseEvent e) {
        //Well set our starting point here on mouse pressed
        AS.setstartxy(e.getX(),e.getY());
        //System.out.println("Mouse Pressed on: " + AS.getstartx() + "     " + AS.getstarty());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //Put the shape factory as a constructor in the create shape command which would take in the application state or a shape factory (preferable)
        AS.setendxy(e.getX(),e.getY());
        switch(AS.getActiveStartAndEndPointMode()) {
            case DRAW:
                command = new CreateShapeCommand(shape, AS.getASPoint(), AS.getJList());
                try {
                    command.run();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                //Well cast it to IUndoable
                IUndoable undoable1 = (IUndoable)command;
                //Well add it to the stack
                AS.addUndo(undoable1);
                break;
            case MOVE:
                command = new MoveShapeCommand(AS.getASPoint(), AS.getJList(), AS.getSJList());
                try {
                    command.run();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                //Well cast it to IUndoable
                IUndoable undoable = (IUndoable)command;
                //Well add it to the stack
                AS.addUndo(undoable);
                command = new UpdateShapeCommand(shape,AS.getJList());
                try {
                    command.run();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
            case SELECT:
                command = new SelectShapeCommand(AS.getASPoint(), AS.getJList(), AS.getSJList());
                try {
                    command.run();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
        }
            //System.out.println("Released from: " + AS.getendx() + "     " + AS.getendy());
    }
}