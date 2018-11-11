package controller;

import model.IShape;
import model.persistence.ApplicationState;
import view.DrawShapeHandler;

import java.util.ArrayList;


public class PasteShapeCommand implements ICommand, IUndoable {
    private DrawShapeHandler draw;
    private ApplicationState AS;
    private ArrayList<IShape> PastedList;

    public PasteShapeCommand(ApplicationState AS, DrawShapeHandler draw) {
        this.AS = AS;
        this.draw = draw;
        this.PastedList = new ArrayList<>();
    }

    @Override
    public void run() {
        JPoint Pt_Offset;
        int width;
        int height;
        int area;
        //First I will iterate through my list of copied items
        for (IShape shape : AS.getCPJList().getShapeList()) {
            //We want to set an offset here for pasting our points
            Pt_Offset = shape.return_new_pts();
            width = Math.abs(Pt_Offset.getendx() - Pt_Offset.getstartx());
            height = Math.abs(Pt_Offset.getendy() - Pt_Offset.getstarty());
            //Recalculate the area
            area = width * height;
            //Set the offset to the shape
            //Register in our new shape location to our CPJList
            PastedList.add(shape);
        }
        //I also want to add them to my Main List
        for (IShape pshapes : PastedList) {
            AS.getJList().registerObserver(pshapes);
        }
        //Redraw everything
        draw.DrawShapes(draw, AS.getJList().getShapeList());
        System.out.println("Current JShapeList Size : " + AS.getJList().getShapeList().size());
    }

    @Override
    public void undo() {
        //Remove pasted shapes from our original list
        for (IShape pshapes : PastedList) {
            AS.getJList().removeObserver(pshapes);
        }
        //Redraw Everything
        draw.DrawShapes(draw, AS.getJList().getShapeList());
        System.out.println("Current JShapeList Size : " + AS.getJList().getShapeList().size());
    }

    @Override
    public void redo() {
        //Add pasted shapes from our original list
        for (IShape pshapes : PastedList) {
            AS.getJList().registerObserver(pshapes);
        }
        //Redraw Everything
        draw.DrawShapes(draw, AS.getJList().getShapeList());
        System.out.println("Current JShapeList Size : " + AS.getJList().getShapeList().size());
    }
    }
