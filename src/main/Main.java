package main;

import controller.*;
import model.ShapeFactory;
import view.DrawShapeHandler;
import view.User_Mouse_Handler;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;

public class Main {
    public static void main(String[] args){
        //PaintCanvas and GUI
        PaintCanvas paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        //Draw and MouseHandler
        DrawShapeHandler draw = new DrawShapeHandler(paintCanvas);
        ApplicationState appState = new ApplicationState(uiModule, draw);
        IJPaintController controller = new JPaintController(uiModule, appState);
        controller.setup();
        ShapeFactory shape = new ShapeFactory(appState, draw);
        User_Mouse_Handler Mouse = new User_Mouse_Handler(shape, appState);
        paintCanvas.addMouseListener(Mouse);
        }
}
