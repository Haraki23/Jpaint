package model.persistence;

import controller.*;
import model.*;
import model.dialogs.DialogProvider;
import model.interfaces.IApplicationState;
import model.interfaces.IDialogProvider;
import view.DrawShapeHandler;
import view.interfaces.IUiModule;
import java.io.IOException;
import java.io.Serializable;
import java.util.Stack;

public class ApplicationState implements IApplicationState, Serializable {
    private static final long serialVersionUID = -5545483996576839007L;
    private final IUiModule uiModule;
    private final IDialogProvider dialogProvider;

    private ShapeType activeShapeType;
    private ShapeColor activePrimaryColor;
    private ShapeColor activeSecondaryColor;
    private ShapeShadingType activeShapeShadingType;
    private StartAndEndPointMode activeStartAndEndPointMode;
    private ICommand command;
    private DrawShapeHandler draw;
    //Our ShapeLists Creation
    private JShapeList JList;
    private CopyPasteJShapeList CPJList;
    private SelectedJShapeList SJList;
    //Our Undo / Redo Stacks
    private Stack<IUndoable> undo_stack;
    private Stack<IUndoable> redo_stack;
    //Start and end points
    int startx;
    int starty;
    int endx;
    int endy;
    JPoint ASPoint;

    public ApplicationState(IUiModule uiModule, DrawShapeHandler draw) {
        this.uiModule = uiModule;
        this.dialogProvider = new DialogProvider(this);
        this.draw = draw;
        this.command = command;
        this.JList = new JShapeList();
        this.CPJList = new CopyPasteJShapeList();
        this.SJList = new SelectedJShapeList();
        this.undo_stack = new Stack<>();
        this.redo_stack = new Stack<>();
        this.startx = startx;
        this.starty = starty;
        this.endx = endx;
        this.endy = endy;
        this.ASPoint = new JPoint();
        setDefaults();
    }
    @Override
    public void setActiveShapeTester(ShapeType test) {
        activeShapeType = test;
    }

    @Override
    public void setActiveShape() {
        activeShapeType = uiModule.getDialogResponse(dialogProvider.getChooseShapeDialog());
    }

    @Override
    public void setActivePrimaryColor() {
        activePrimaryColor = uiModule.getDialogResponse(dialogProvider.getChoosePrimaryColorDialog());
    }

    @Override
    public void setActiveSecondaryColor() {
        activeSecondaryColor = uiModule.getDialogResponse(dialogProvider.getChooseSecondaryColorDialog());
    }

    @Override
    public void setActiveShadingType() {
        activeShapeShadingType = uiModule.getDialogResponse(dialogProvider.getChooseShadingTypeDialog());
    }

    @Override
    public void setActiveStartAndEndPointMode() {
        activeStartAndEndPointMode = uiModule.getDialogResponse(dialogProvider.getChooseStartAndEndPointModeDialog());
    }

    @Override
    public ShapeType getActiveShapeType() {
        return activeShapeType;
    }

    @Override
    public ShapeColor getActivePrimaryColor() {
        return activePrimaryColor;
    }

    @Override
    public ShapeColor getActiveSecondaryColor() {
        return activeSecondaryColor;
    }

    @Override
    public ShapeShadingType getActiveShapeShadingType() {
        return activeShapeShadingType;
    }

    @Override
    public StartAndEndPointMode getActiveStartAndEndPointMode() {
        return activeStartAndEndPointMode;
    }

    @Override
    public void copy(){
        command = new CopyShapeCommand(this, SJList, CPJList);
        command.run();

    }
    @Override
    public void undo() {
        if (undo_stack.size() == 0) {return;}
        IUndoable command = undo_stack.pop();
        redo_stack.add(command);
        command.undo();
        draw.DrawShapes(draw, JList.getShapeList());
        System.out.println("<<<Undo Stack Size>>> " + undo_stack.size());
    }

    @Override
    public void redo() {
        if (redo_stack.size() == 0) {return;}
        IUndoable command = redo_stack.pop();
        undo_stack.add(command);
        command.redo();
        draw.DrawShapes(draw, JList.getShapeList());
        System.out.println("<<<Redo Stack Size>>> " + redo_stack.size());
    }

    @Override
    public void paste() {
        command = new PasteShapeCommand(this, draw);
        command.run();
        //Well cast it to IUndoable
        IUndoable undoable = (IUndoable)command;
        //Well add it to the stack
        undo_stack.add(undoable);
        System.out.println("<<<Undo Stack Size>>> " + undo_stack.size());
    }

    @Override

    public void delete(){
        command = new DeleteShapeCommand(this, draw);
        command.run();
        //Well cast it to IUndoable
        IUndoable undoable = (IUndoable)command;
        //Well add it to the stack
        undo_stack.add(undoable);
        System.out.println("This is the current size of our undo stack " + undo_stack.size());
    }

    @Override

    public JShapeList getJList(){

        return JList;

    }
    @Override

    public CopyPasteJShapeList getCPJList(){

        return CPJList;

    }

    public void setstartxy(int x, int y){
        this.startx = x;
        this.starty = y;
        this.ASPoint.setstartx(startx);
        this.ASPoint.setstarty(starty);
    }

    public void setendxy(int x, int y){
        this.endx = x;
        this.endy = y;
        this.ASPoint.setendx(endx);
        this.ASPoint.setendy(endy);
    }

    public int getstartx(){

        return startx;

    }
    public int getstarty(){

        return starty;

    }
    public int getendx(){

        return endx;

    }
    public int getendy(){

        return endy;

    }

    public void addUndo(IUndoable command){
        undo_stack.add(command);
    }

    public void addRedo(IUndoable command){
        redo_stack.add(command);
    }

    public JPoint getASPoint(){
        return ASPoint;
    }

    public SelectedJShapeList getSJList(){
        return SJList;
    }

    private void setDefaults() {
        activeShapeType = ShapeType.RECTANGLE;
        activePrimaryColor = ShapeColor.GRAY;
        activeSecondaryColor = ShapeColor.ORANGE;
        activeShapeShadingType = ShapeShadingType.OUTLINE_AND_FILLED_IN;
        activeStartAndEndPointMode = StartAndEndPointMode.DRAW;
    }
}
