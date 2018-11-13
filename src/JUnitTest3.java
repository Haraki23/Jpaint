import controller.*;
import model.*;
import model.persistence.ApplicationState;
import org.junit.Test;
import view.DrawShapeHandler;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class JUnitTest3 {
    //PaintCanvas and GUI
    PaintCanvas paintCanvas = new PaintCanvas();
    IGuiWindow guiWindow = new GuiWindow(paintCanvas);
    IUiModule uiModule = new Gui(guiWindow);
    //Draw and MouseHandler
    DrawShapeHandler draw = new DrawShapeHandler(paintCanvas);
    ApplicationState appState = new ApplicationState(uiModule, draw);
    IJPaintController controller = new JPaintController(uiModule, appState, paintCanvas);
    ShapeFactory shape = new ShapeFactory(appState, draw);
    //The first UnitTest will check if we are adding shapes successfully to our shapelist using our ShapeFactory
    @Test
    public void testremoveShape() {
        controller.setup();
        ICommand command;
        //This Unit test will test if our Null Object Pattern will handle if we pass in draw as a null object to our shapeFactory

        //Should be empty now
        assertEquals(appState.getJList().getShapeList().size(), 0);
    }

}
