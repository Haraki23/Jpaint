package main;

import controller.IJPaintController;
import controller.JPaintController;
import model.ShapeFactory;
import view.User_Mouse_Handler;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;

import java.awt.*;

public class Main {
    public static void main(String[] args){
        PaintCanvas paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        IJPaintController controller = new JPaintController(uiModule, appState);
        controller.setup();
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        User_Mouse_Handler Mouse = new User_Mouse_Handler(appState, graphics2d);
        paintCanvas.addMouseListener(Mouse);
        graphics2d.setStroke(new BasicStroke(5));

        }
}
