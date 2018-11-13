package main;

import controller.*;
import model.ShapeFactory;
import view.DrawShapeHandler;
import view.User_Keyboard_Handler;
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
        PaintCanvas paintCanvas = PaintCanvas.getInstance();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        //Draw and MouseHandler
        DrawShapeHandler draw = new DrawShapeHandler(paintCanvas);
        ApplicationState appState = new ApplicationState(uiModule, draw);
        IJPaintController controller = new JPaintController(uiModule, appState, paintCanvas);
        controller.setup();
        ShapeFactory shape = new ShapeFactory(appState, draw);
        User_Mouse_Handler Mouse = new User_Mouse_Handler(shape, appState);
        User_Keyboard_Handler key = new User_Keyboard_Handler(shape, appState);
        paintCanvas.addMouseListener(Mouse);
        paintCanvas.addKeyListener(key);
        paintCanvas.requestFocus();
    }
}
