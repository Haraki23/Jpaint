package main;

import controller.*;
import controller.Interfaces.IJPaintController;
import model.Factories.ShapeFactory;
import view.Handlers.DrawShapeHandler;
import view.Handlers.User_Keyboard_Handler;
import view.Handlers.User_Mouse_Handler;
import model.Persistence.ApplicationState;
import view.GUI.Gui;
import view.GUI.GuiWindow;
import view.GUI.PaintCanvas;
import view.Interfaces.IGuiWindow;
import view.Interfaces.IUiModule;

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
